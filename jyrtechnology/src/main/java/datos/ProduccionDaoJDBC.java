package datos;

import dominio.*;
import java.util.*;
import java.sql.*;
import util.Conexion;

public class ProduccionDaoJDBC implements ProduccionDao {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT_PRODUCCION_BY_IDPEDIDO = "SELECT idpd, estado, fecha, cantavance"
            + " FROM vista_produccion WHERE idped = ? ORDER BY idpd ASC";
    private static final String SQL_INSERT_PRODUCCION = "INSERT INTO produccionpedido(idped ,estado ,fecha ) VALUES(?,?,?)";

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
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            
            //se registra la producciona inicial del pedido
            stmt = conn.prepareStatement(SQL_INSERT_PRODUCCION);
            stmt.setInt(1, idPedido);
            stmt.setString(2, estado);
            stmt.setString(3, fecha);
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

}
