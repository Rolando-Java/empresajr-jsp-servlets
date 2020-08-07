package datos;

import dominio.*;
import java.util.*;
import java.sql.*;
import util.Conexion;

public class FichaTecnicaDaoJDBC implements FichaTecnicaDao {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT_FICHATECNICA_BY_IDFICHATECNICA = "SELECT composicion ,color ,disenio ,bordado ,estampados ,tipocuello ,comentario"
            + " FROM fictecnica WHERE idft = ?";
    private static final String SQL_SELECT_ETIQUETAS_BY_IDFICHATECNICA = "SELECT idetiq,etiqueta FROM vista_fichatecnica_etiquetas"
            + " WHERE idft = ? ORDER BY idetiq ASC";
    private static final String SQL_SELECT_TALLAS_BY_IDFICHATECNICA = "SELECT idma, talla, cantidad FROM vista_fichatecnica_tallas"
            + " WHERE idft = ? ORDER BY idma ASC";
    private static final String SQL_SELECT_TALLAS_MEDIDASATOMICAS_BY_IDTALLA = "SELECT idtm, descripme, medida FROM vista_tallas_medidasatomicas"
            + " WHERE idma = ? ORDER BY idtm ASC";
    private static final String SQL_INSERT_FICHATECNICA = "INSERT INTO fictecnica (composicion ,color ,disenio ,bordado ,estampados ,tipocuello ,comentario )"
            + " VALUES(?,?,?,?,?,?,?) RETURNING idft";
    private static final String SQL_INSERT_FICHATECNICA_ETIQUETA = "INSERT INTO fictec_etiq(idft ,idetiq ) VALUES(?,?)";
    private static final String SQL_INSERT_TALLA = "INSERT INTO tallas(idft ,talla ,cantidad ) VALUES(?,?,?) RETURNING idma";
    private static final String SQL_INSERT_MEDIDAS = "INSERT INTO medidas(idma ,idtm ,medida ) VALUES(?,?,?)";
    private static final String SQL_UPDATE_PEDIDO_ID_FICHATECNICA = "UPDATE pedido SET idft = ? WHERE idped = ?";

    public FichaTecnicaDaoJDBC() {

    }

    public FichaTecnicaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public void generarFichaTecnica(FichaTecnica fichaTecnica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FichaTecnicaEtiqueta> fichaTecnicaEtiquetas = null;
        FichaTecnicaEtiqueta fichaTecnicaEtiqueta = null;
        Etiqueta etiqueta = null;
        List<Talla> tallas = null;
        Talla talla = null;
        List<TallaMedidaAtomica> tallaMedidaAtomicas = null;
        TallaMedidaAtomica tallaMedidaAtomica = null;
        MedidaAtomica medidaAtomica = null;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_FICHATECNICA_BY_IDFICHATECNICA);
            stmt.setInt(1, fichaTecnica.getIdFichaTecnica());
            rs = stmt.executeQuery();

            //construyendo la ficha tecnica
            if (rs.next()) {
                String composicion = rs.getString("composicion");
                String color = rs.getString("color");
                String disenio = rs.getString("disenio");
                String bordado = rs.getString("bordado");
                String estampado = rs.getString("estampados");
                String tipoCuello = rs.getString("tipocuello");
                String comentario = rs.getString("comentario");

                fichaTecnica.setComposicion(composicion);
                fichaTecnica.setColor(color);
                fichaTecnica.setDisenio(disenio);
                fichaTecnica.setBordado(bordado);
                fichaTecnica.setEstampado(estampado);
                fichaTecnica.setTipoCuello(tipoCuello);
                fichaTecnica.setComentario(comentario);
            }

            //Obteniendo etiquetas de la ficha tecnica
            stmt = conn.prepareStatement(SQL_SELECT_ETIQUETAS_BY_IDFICHATECNICA);
            stmt.setInt(1, fichaTecnica.getIdFichaTecnica());
            rs = stmt.executeQuery();
            fichaTecnicaEtiquetas = new ArrayList<FichaTecnicaEtiqueta>();

            while (rs.next()) {
                int idEtiqueta = rs.getInt("idetiq");
                String nombreEtiqueta = rs.getString("etiqueta");

                etiqueta = new Etiqueta(idEtiqueta, nombreEtiqueta);
                fichaTecnicaEtiqueta = new FichaTecnicaEtiqueta(etiqueta);
                fichaTecnicaEtiquetas.add(fichaTecnicaEtiqueta);
            }
            fichaTecnica.setFichaTecnicaEtiquetas(fichaTecnicaEtiquetas);

            //Obteniendo las tallas de la ficha tecnica
            stmt = conn.prepareStatement(SQL_SELECT_TALLAS_BY_IDFICHATECNICA);
            stmt.setInt(1, fichaTecnica.getIdFichaTecnica());
            rs = stmt.executeQuery();
            tallas = new ArrayList<Talla>();

            while (rs.next()) {
                int idTalla = rs.getInt("idma");
                String nombreTalla = rs.getString("talla");
                int cantidad = rs.getInt("cantidad");

                talla = new Talla(idTalla, nombreTalla, cantidad);
                tallas.add(talla);
            }
            fichaTecnica.setTallas(tallas);

            //Obtiendo medidas atomicas de las tallas
            stmt = conn.prepareStatement(SQL_SELECT_TALLAS_MEDIDASATOMICAS_BY_IDTALLA);
            for (Talla tallaObj : tallas) {
                stmt.setInt(1, talla.getIdTalla());
                rs = stmt.executeQuery();
                tallaMedidaAtomicas = new ArrayList<TallaMedidaAtomica>();

                while (rs.next()) {
                    int idMedidaAtomica = rs.getInt("idtm");
                    String descripcion = rs.getString("descripme");
                    String medida = rs.getString("medida");

                    medidaAtomica = new MedidaAtomica(idMedidaAtomica, descripcion);
                    tallaMedidaAtomica = new TallaMedidaAtomica(medidaAtomica, medida);
                    tallaMedidaAtomicas.add(tallaMedidaAtomica);
                }
                tallaObj.setTallaMedidaAtomicas(tallaMedidaAtomicas);
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

    }

    @Override
    public int ingresarFichaTecnica(int idPedido, FichaTecnica fichaTecnica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idFichaTecnica = 0;
        try {
            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            //se registra la ficha tecnica
            stmt = conn.prepareStatement(SQL_INSERT_FICHATECNICA);
            stmt.setString(1, fichaTecnica.getComposicion());
            stmt.setString(2, fichaTecnica.getColor());
            stmt.setString(3, fichaTecnica.getDisenio());
            stmt.setString(4, fichaTecnica.getBordado());
            stmt.setString(5, fichaTecnica.getEstampado());
            stmt.setString(6, fichaTecnica.getTipoCuello());
            stmt.setString(7, fichaTecnica.getComentario());
            stmt.execute();

            //Se obtiene el id de la ficha tecnica registrada
            rs = stmt.getResultSet();

            if (rs.next()) {
                idFichaTecnica = rs.getInt("idft");
            }

            //Se registra el id de la ficha tecnica en el pedido
            stmt = conn.prepareStatement(SQL_UPDATE_PEDIDO_ID_FICHATECNICA);
            stmt.setInt(1, idFichaTecnica);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            Conexion.close(stmt);
            if (conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return idFichaTecnica;
    }

    @Override
    public void registrarDetalleFichaTecnica(FichaTecnica fichaTecnica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FichaTecnicaEtiqueta> fichaTecnicaEtiquetas = null;
        List<Talla> tallas = null;
        List<TallaMedidaAtomica> tallaMedidaAtomicas = null;

        try {

            conn = (conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            
            //Registrando las etiquetas de la ficha tecnica
            stmt = conn.prepareStatement(SQL_INSERT_FICHATECNICA_ETIQUETA);
            fichaTecnicaEtiquetas = fichaTecnica.getFichaTecnicaEtiquetas();

            for (FichaTecnicaEtiqueta fichaTecnicaEtiquetaObj : fichaTecnicaEtiquetas) {
                stmt.setInt(1, fichaTecnica.getIdFichaTecnica());
                stmt.setInt(2, fichaTecnicaEtiquetaObj.getEtiqueta().getIdEtiqueta());
                stmt.executeUpdate();
            }

            //Registrando las tallas de la ficha tecnica
            tallas = fichaTecnica.getTallas();

            for (Talla tallaObj : tallas) {
                stmt = conn.prepareStatement(SQL_INSERT_TALLA);
                stmt.setInt(1, fichaTecnica.getIdFichaTecnica());
                stmt.setString(2, tallaObj.getTalla());
                stmt.setInt(3, tallaObj.getCantidad());
                stmt.execute();

                //se obtiene el id de la talla registrada
                rs = stmt.getResultSet();
                int idTalla = 0;
                
                if (rs.next()) {
                    idTalla = rs.getInt("idma");
                }

                //Registrando las medidas atomicas que corresponden a esa talla
                stmt = conn.prepareStatement(SQL_INSERT_MEDIDAS);
                tallaMedidaAtomicas = tallaObj.getTallaMedidaAtomicas();

                for (TallaMedidaAtomica tallaMedidaAtomicaObj : tallaMedidaAtomicas) {
                    stmt.setInt(1, idTalla);
                    stmt.setInt(2, tallaMedidaAtomicaObj.getMedidaAtomica().getIdMedidaAtomica());
                    stmt.setString(3, tallaMedidaAtomicaObj.getMedida());
                    stmt.executeUpdate();
                }
            }
            
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
