package datos;

import dominio.FichaTecnica;
import dominio.Pedido;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import util.Conexion;

public class PedidoDaoJDBC implements PedidoDao {

    private static final String SQL_SELECT_PEDIDO_BY_IDUSUARO = "SELECT idped ,coalesce (idft,0) as idficha,SUM(subtot) AS total ,SUM(canpren) as cantPrendTotal,fec ,hor ,estado "
            + "FROM vista_pedido_detallepedido GROUP BY idped, idft ,fec ,hor ,estado ,idusu HAVING idusu = ? ORDER BY fec asc, hor asc";
    private static final String SQL_SELECT_PEDIDO_BY_ESTADO_IDUSUARIO = "SELECT idped ,coalesce (idft,0) as idficha,SUM(subtot) AS total ,SUM(canpren) as cantPrendTotal,fec ,hor ,estado "
            + "FROM vista_pedido_detallepedido GROUP BY idped, idft ,fec ,hor ,estado , idusu HAVING estado = ? and idusu = ? ORDER BY fec asc, hor asc";

    @Override
    public List<Pedido> listarPedidos(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        FichaTecnica fichaTecnica = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PEDIDO_BY_IDUSUARO);
            stmt.setInt(1, idUsuario);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("idped");
                int idFichaTecnica = rs.getInt("idficha");
                BigDecimal total = rs.getBigDecimal("total");
                int cantidadPrendasTotal = rs.getInt("cantPrendTotal");
                String fecha = rs.getString("fec");
                String hora = rs.getString("hor");
                String estado = rs.getString("estado");

                fichaTecnica = new FichaTecnica(idFichaTecnica);
                pedido = new Pedido(idPedido, fecha, hora, estado, cantidadPrendasTotal, total, fichaTecnica);
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pedidos;
    }

    @Override
    public List<Pedido> listarPedidos(int idUsuario, String estadoPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        FichaTecnica fichaTecnica = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PEDIDO_BY_ESTADO_IDUSUARIO);
            stmt.setString(1, estadoPedido);
            stmt.setInt(2, idUsuario);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("idped");
                int idFichaTecnica = rs.getInt("idficha");
                BigDecimal total = rs.getBigDecimal("total");
                int cantidadPrendasTotal = rs.getInt("cantPrendTotal");
                String fecha = rs.getString("fec");
                String hora = rs.getString("hor");
                String estado = rs.getString("estado");

                fichaTecnica = new FichaTecnica(idFichaTecnica);
                pedido = new Pedido(idPedido, fecha, hora, estado, cantidadPrendasTotal, total, fichaTecnica);
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pedidos;
    }

}
