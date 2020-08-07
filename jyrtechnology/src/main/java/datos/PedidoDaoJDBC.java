package datos;

import constantes.EstadoProduccion;
import dominio.*;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

public class PedidoDaoJDBC implements PedidoDao {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT_PEDIDO_BY_IDUSUARO = "SELECT idped ,coalesce (idft,0) as idficha,SUM(subtot) AS total ,SUM(canpren) as cantPrendTotal,fec ,hor ,estado "
            + "FROM vista_pedido_detallepedido GROUP BY idped, idft ,fec ,hor ,estado ,idusu HAVING idusu = ? ORDER BY TO_DATE(fec ,'DD-MM-YYYY') ASC, CAST(hor as time) ASC";
    private static final String SQL_SELECT_PEDIDO_BY_ESTADO_IDUSUARIO = "SELECT idped ,coalesce (idft,0) as idficha,SUM(subtot) AS total ,SUM(canpren) as cantPrendTotal,fec ,hor ,estado "
            + "FROM vista_pedido_detallepedido GROUP BY idped, idft ,fec ,hor ,estado , idusu HAVING estado = ? and idusu = ? ORDER BY TO_DATE(fec ,'DD-MM-YYYY') ASC, CAST(hor as time) ASC";
    private static final String SQL_SELECT_PEDIDO = "SELECT idusu ,idped ,coalesce (idft,0) as idficha,SUM(subtot) AS total ,SUM(canpren) as cantPrendTotal,fec ,hor ,estado "
            + "FROM vista_pedido_detallepedido GROUP BY idped, idft ,fec ,hor ,estado ,idusu ORDER BY TO_DATE(fec ,'DD-MM-YYYY') ASC, CAST(hor as time) ASC";
    private static final String SQL_SELECT_DETALLEPEDIDO_BY_IDPEDIDO = "SELECT idpren ,tippren ,preuni ,subtot ,canpren "
            + "FROM vista_pedido_detallepedido_prenda WHERE idped = ? ORDER BY idpren ASC ";
    private static final String SQL_SELECT_PRENDA = "SELECT idpren ,tippren ,preuni FROM prendas ORDER BY idpren ASC";
    private static final String SQL_INSERT_PEDIDO = "INSERT INTO pedido(idusu ,fec ,hor ,estado ) VALUES(?,?,?,?) RETURNING idped";
    private static final String SQL_INSERT_DETALLE_PEDIDO = "INSERT INTO detallepedido(idped ,idpren ,subtot ,canpren ) VALUES(?,?,?,?)";

    public PedidoDaoJDBC() {

    }

    public PedidoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<Pedido> listarPedidos(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        Usuario usuario = null;
        List<DetallePedido> detallePedidos = null;
        DetallePedido detallePedido = null;
        Prenda prenda = null;
        FichaTecnica fichaTecnica = null;
        List<Produccion> producciones = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            //Se obtiene el usuario que ha realizado los pedidos
            UsuarioDao usuarioJDBC = new UsuarioDaoJDBC(conn);
            usuario = usuarioJDBC.listarUsuarios(idUsuario);

            stmt = conn.prepareStatement(SQL_SELECT_PEDIDO_BY_IDUSUARO);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            pedidos = new ArrayList<Pedido>();

            //Se obtiene todos los pedidos realizados por un usuario
            while (rs.next()) {
                int idPedido = rs.getInt("idped");
                int idFichaTecnica = rs.getInt("idficha");
                String fecha = rs.getString("fec");
                String hora = rs.getString("hor");
                String estado = rs.getString("estado");
                int cantidadPrendasTotal = rs.getInt("cantPrendTotal");
                BigDecimal total = rs.getBigDecimal("total");

                fichaTecnica = new FichaTecnica(idFichaTecnica);
                pedido = new Pedido(idPedido, usuario, fecha, hora, estado, cantidadPrendasTotal, total, fichaTecnica);
                pedidos.add(pedido);
            }

            //Se obtiene el detalle de cada pedido
            stmt = conn.prepareStatement(SQL_SELECT_DETALLEPEDIDO_BY_IDPEDIDO);
            for (Pedido pedidoObj : pedidos) {
                stmt.setInt(1, pedidoObj.getIdPedido());
                rs = stmt.executeQuery();
                detallePedidos = new ArrayList<DetallePedido>();

                while (rs.next()) {
                    int idPrenda = rs.getInt("idpren");
                    String tipoPrenda = rs.getString("tippren");
                    BigDecimal precioUnitario = rs.getBigDecimal("preuni");
                    BigDecimal subTotal = rs.getBigDecimal("subtot");
                    int cantidadPrendas = rs.getInt("canpren");

                    prenda = new Prenda(idPrenda, tipoPrenda, precioUnitario);
                    detallePedido = new DetallePedido(prenda, subTotal, cantidadPrendas);
                    detallePedidos.add(detallePedido);
                }
                pedidoObj.setDetallePedidos(detallePedidos);
            }

            //Se obtiene la ficha tecnica de cada pedido
            FichaTecnicaDao fichaTecnicaJDBC = new FichaTecnicaDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                if (pedidoObj.getFichaTecnica().getIdFichaTecnica() > 0) {
                    fichaTecnicaJDBC.generarFichaTecnica(pedidoObj.getFichaTecnica());
                }
            }

            //Obtener la produccion de cada pedido
            ProduccionDao produccionJDBC = new ProduccionDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                producciones = produccionJDBC.obtenerProduccion(pedidoObj.getIdPedido());
                pedidoObj.setProducciones(producciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return pedidos;
    }

    @Override
    public List<Pedido> listarPedidos(int idUsuario, String estadoPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        Usuario usuario = null;
        List<DetallePedido> detallePedidos = null;
        DetallePedido detallePedido = null;
        Prenda prenda = null;
        FichaTecnica fichaTecnica = null;
        List<Produccion> producciones = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            //Se obtiene el usuario que ha realizado los pedidos
            UsuarioDao usuarioJDBC = new UsuarioDaoJDBC(conn);
            usuario = usuarioJDBC.listarUsuarios(idUsuario);

            stmt = conn.prepareStatement(SQL_SELECT_PEDIDO_BY_ESTADO_IDUSUARIO);
            stmt.setString(1, estadoPedido);
            stmt.setInt(2, idUsuario);
            rs = stmt.executeQuery();
            pedidos = new ArrayList<Pedido>();

            //Se obtiene todos los pedidos realizados por un usuario segun el estado del pedido
            while (rs.next()) {
                int idPedido = rs.getInt("idped");
                int idFichaTecnica = rs.getInt("idficha");
                String fecha = rs.getString("fec");
                String hora = rs.getString("hor");
                String estado = rs.getString("estado");
                int cantidadPrendasTotal = rs.getInt("cantPrendTotal");
                BigDecimal total = rs.getBigDecimal("total");

                fichaTecnica = new FichaTecnica(idFichaTecnica);
                pedido = new Pedido(idPedido, usuario, fecha, hora, estado, cantidadPrendasTotal, total, fichaTecnica);
                pedidos.add(pedido);
            }

            //Se obtiene el detalle de cada pedido
            stmt = conn.prepareStatement(SQL_SELECT_DETALLEPEDIDO_BY_IDPEDIDO);
            for (Pedido pedidoObj : pedidos) {
                stmt.setInt(1, pedidoObj.getIdPedido());
                rs = stmt.executeQuery();
                detallePedidos = new ArrayList<DetallePedido>();

                while (rs.next()) {
                    int idPrenda = rs.getInt("idpren");
                    String tipoPrenda = rs.getString("tippren");
                    BigDecimal precioUnitario = rs.getBigDecimal("preuni");
                    BigDecimal subTotal = rs.getBigDecimal("subtot");
                    int cantidadPrendas = rs.getInt("canpren");

                    prenda = new Prenda(idPrenda, tipoPrenda, precioUnitario);
                    detallePedido = new DetallePedido(prenda, subTotal, cantidadPrendas);
                    detallePedidos.add(detallePedido);
                }
                pedidoObj.setDetallePedidos(detallePedidos);
            }

            //Se obtiene la ficha tecnica de cada pedido
            FichaTecnicaDao fichaTecnicaJDBC = new FichaTecnicaDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                if (pedidoObj.getFichaTecnica().getIdFichaTecnica() > 0) {
                    fichaTecnicaJDBC.generarFichaTecnica(pedidoObj.getFichaTecnica());
                }
            }

            //Obtener la produccion de cada pedido
            ProduccionDao produccionJDBC = new ProduccionDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                producciones = produccionJDBC.obtenerProduccion(pedidoObj.getIdPedido());
                pedidoObj.setProducciones(producciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return pedidos;
    }

    @Override
    public List<Pedido> listarPedidos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = null;
        Pedido pedido = null;
        Usuario usuario = null;
        List<DetallePedido> detallePedidos = null;
        DetallePedido detallePedido = null;
        Prenda prenda = null;
        FichaTecnica fichaTecnica = null;
        List<Produccion> producciones = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_SELECT_PEDIDO);
            rs = stmt.executeQuery();
            pedidos = new ArrayList<Pedido>();
            UsuarioDao usuarioJDBC = new UsuarioDaoJDBC(conn);

            //Se obtiene todos los pedidos realizados
            while (rs.next()) {
                int idUsuario = rs.getInt("idusu");
                int idPedido = rs.getInt("idped");
                int idFichaTecnica = rs.getInt("idficha");
                String fecha = rs.getString("fec");
                String hora = rs.getString("hor");
                String estado = rs.getString("estado");
                int cantidadPrendasTotal = rs.getInt("cantPrendTotal");
                BigDecimal total = rs.getBigDecimal("total");

                //Se obtiene el usuario que ha realizado los pedidos
                usuario = usuarioJDBC.listarUsuarios(idUsuario);

                fichaTecnica = new FichaTecnica(idFichaTecnica);
                pedido = new Pedido(idPedido, usuario, fecha, hora, estado, cantidadPrendasTotal, total, fichaTecnica);
                pedidos.add(pedido);
            }

            //Se obtiene el detalle de cada pedido
            stmt = conn.prepareStatement(SQL_SELECT_DETALLEPEDIDO_BY_IDPEDIDO);
            for (Pedido pedidoObj : pedidos) {
                stmt.setInt(1, pedidoObj.getIdPedido());
                rs = stmt.executeQuery();
                detallePedidos = new ArrayList<DetallePedido>();

                while (rs.next()) {
                    int idPrenda = rs.getInt("idpren");
                    String tipoPrenda = rs.getString("tippren");
                    BigDecimal precioUnitario = rs.getBigDecimal("preuni");
                    BigDecimal subTotal = rs.getBigDecimal("subtot");
                    int cantidadPrendas = rs.getInt("canpren");

                    prenda = new Prenda(idPrenda, tipoPrenda, precioUnitario);
                    detallePedido = new DetallePedido(prenda, subTotal, cantidadPrendas);
                    detallePedidos.add(detallePedido);
                }
                pedidoObj.setDetallePedidos(detallePedidos);
            }

            //Se obtiene la ficha tecnica de cada pedido
            FichaTecnicaDao fichaTecnicaJDBC = new FichaTecnicaDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                if (pedidoObj.getFichaTecnica().getIdFichaTecnica() > 0) {
                    fichaTecnicaJDBC.generarFichaTecnica(pedidoObj.getFichaTecnica());
                }
            }

            //Obtener la produccion de cada pedido
            ProduccionDao produccionJDBC = new ProduccionDaoJDBC(conn);
            for (Pedido pedidoObj : pedidos) {
                producciones = produccionJDBC.obtenerProduccion(pedidoObj.getIdPedido());
                pedidoObj.setProducciones(producciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return pedidos;
    }

    @Override
    public List<Prenda> listarPrendas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Prenda> prendas = null;
        Prenda prenda = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            //se obtienen todas las prendas
            stmt = conn.prepareStatement(SQL_SELECT_PRENDA);
            rs = stmt.executeQuery();
            prendas = new ArrayList<Prenda>();

            while (rs.next()) {
                int idPrenda = rs.getInt("idpren");
                String tipoPrenda = rs.getString("tippren");
                BigDecimal precioUnitario = rs.getBigDecimal("preuni");

                prenda = new Prenda(idPrenda, tipoPrenda, precioUnitario);
                prendas.add(prenda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return prendas;
    }

    @Override
    public void registrarPedido(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetallePedido> detallePedidos = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            //Se ingresa el pedido
            stmt = conn.prepareStatement(SQL_INSERT_PEDIDO);
            stmt.setInt(1, pedido.getUsuario().getIdUsuario());
            stmt.setString(2, pedido.getFecha());
            stmt.setString(3, pedido.getHora());
            stmt.setString(4, pedido.getEstado());
            stmt.execute();

            //Se obtiene el id del pedido ingresado
            rs = stmt.getResultSet();
            int idPedido = 0;

            if (rs.next()) {
                idPedido = rs.getInt("idped");
            }

            //Se ingresa el detalle del pedido
            stmt = conn.prepareStatement(SQL_INSERT_DETALLE_PEDIDO);
            detallePedidos = pedido.getDetallePedidos();

            for (DetallePedido detallePedidoObj : detallePedidos) {
                stmt.setInt(1, idPedido);
                stmt.setInt(2, detallePedidoObj.getPrenda().getIdPrenda());
                stmt.setBigDecimal(3, detallePedidoObj.getSubTotal());
                stmt.setInt(4, detallePedidoObj.getCantidadPrendas());
                stmt.executeUpdate();
            }

            //Se ingresa la produccion inicial del pedido
            ProduccionDao produccionJDBC = new ProduccionDaoJDBC(conn);
            produccionJDBC.registrarProduccion(idPedido, EstadoProduccion.PRODUCCION_INICIADA.getEstado(), pedido.getFecha());

            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

}
