package datos;

import constantes.EstadoPedido;
import dominio.*;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import util.Conexion;

public class ProduccionDaoJDBC implements ProduccionDao {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT_PRODUCCION_BY_IDPEDIDO = "SELECT idpd, estado, fecha, cantavance "
            + "FROM vista_produccion WHERE idped = ? ORDER BY idpd ASC";
    private static final String SQL_SELECT_PEDIDOSPRODUCCION_BY_ESTADOPEDIDO = "SELECT idusu ,idped ,idficha ,total ,cantPrendTotal ,fec ,hor ,estadoPedido "
            + "FROM vista_fechaproduccion WHERE estadoPedido = ? and idficha > 0 and estadoProduccion "
            + "IN ('produccioniniciada','produccionpausa','produccionavance','produccionterminada') "
            + "ORDER BY TO_DATE(fec ,'DD-MM-YYYY') ASC, CAST(hor as time) ASC";
    private static final String SQL_SELECT_PEDIDOSPRODUCCION_BY_ESTADOPEDIDO_ESTADOPRODUCCION = "SELECT idusu ,idped ,idficha ,total ,cantPrendTotal ,fec ,hor ,estadoPedido "
            + "FROM vista_fechaproduccion WHERE estadoPedido = ? and idficha > 0 "
            + "and estadoProduccion = ? ORDER BY TO_DATE(fec ,'DD-MM-YYYY') ASC, CAST(hor as time) ASC";
    private static final String SQL_SELECT_DETALLEPEDIDO_BY_IDPEDIDO = "SELECT idpren ,tippren ,preuni ,subtot ,canpren "
            + "FROM vista_pedido_detallepedido_prenda WHERE idped = ? ORDER BY idpren ASC ";
    private static final String SQL_SELECT_CANTIDADAVANZADA_BY_IDPEDIDO = "SELECT cantavance FROM produccionpedido "
            + "WHERE idpd  = (SELECT MAX(idpd ) FROM produccionpedido WHERE idped = ?)";
    private static final String SQL_INSERT_PRODUCCION = "INSERT INTO produccionpedido(idped ,estado ,fecha ) VALUES(?,?,?)";
    private static final String SQL_INSERT_PRODUCCION_CANTIDADAVANZADA = "INSERT into produccionpedido(idped ,estado ,fecha ,cantavance ) VALUES(?,?,?,?)";

    public ProduccionDaoJDBC() {

    }

    public ProduccionDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<Produccion> obtenerProduccion(int idPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produccion> producciones = null;
        Produccion produccion = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            //se obtiene la produccion
            stmt = conn.prepareStatement(SQL_SELECT_PRODUCCION_BY_IDPEDIDO);
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();
            producciones = new ArrayList<Produccion>();

            while (rs.next()) {
                int idProduccion = rs.getInt("idpd");
                String estado = rs.getString("estado");
                String fecha = rs.getString("fecha");
                int cantidadAvance = rs.getInt("cantavance");

                produccion = new Produccion(idProduccion, estado, fecha, cantidadAvance);
                producciones.add(produccion);
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
        return producciones;
    }

    @Override
    public void registrarProduccion(int idPedido, String estado, String fecha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();

            //se obtiene la ultima cantidad avzanda en caso haya
            stmt = conn.prepareStatement(SQL_SELECT_CANTIDADAVANZADA_BY_IDPEDIDO);
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();
            int cantidadAvance = 0;

            if (rs.next()) {
                cantidadAvance = rs.getInt("cantavance");
            }

            //se valida que exista un avance previo
            if (cantidadAvance != 0) {
                stmt = conn.prepareStatement(SQL_INSERT_PRODUCCION_CANTIDADAVANZADA);
                stmt.setInt(1, idPedido);
                stmt.setString(2, estado);
                stmt.setString(3, fecha);
                stmt.setInt(4, cantidadAvance);
            } else {
                //se registra la produccion inicial del pedido
                stmt = conn.prepareStatement(SQL_INSERT_PRODUCCION);
                stmt.setInt(1, idPedido);
                stmt.setString(2, estado);
                stmt.setString(3, fecha);
            }
            
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }
    }

    @Override
    public List<Pedido> listarPedidos(String estadoPedido) {
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

            /*
            se obtiene los pedidos que estan aceptado, tienen ficha tecnica y un estado de produccion
            como produccioniniciada o produccionpausa o produccionavance o produccionterminada
             */
            stmt = conn.prepareStatement(SQL_SELECT_PEDIDOSPRODUCCION_BY_ESTADOPEDIDO);
            stmt.setString(1, estadoPedido);

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
                String estado = rs.getString("estadoPedido");
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
    public List<Pedido> listarPedidos(String estadoPedido, String estadoProduccion) {
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

            /*
            se obtiene los pedidos que estan aceptado, tienen ficha tecnica y un estado de produccion
            en especifico
             */
            stmt = conn.prepareStatement(SQL_SELECT_PEDIDOSPRODUCCION_BY_ESTADOPEDIDO_ESTADOPRODUCCION);
            stmt.setString(1, estadoPedido);
            stmt.setString(2, estadoProduccion);

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
                String estado = rs.getString("estadoPedido");
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
}
