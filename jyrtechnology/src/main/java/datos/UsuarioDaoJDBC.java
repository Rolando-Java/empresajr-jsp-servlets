package datos;

import constantes.MensajeError;
import constantes.TipoImagen;
import dominio.Correo;
import dominio.Usuario;
import excepciones.CorreoException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import util.EmailGenerator;

public class UsuarioDaoJDBC implements UsuarioDao {

    private static final String SQL_INSERT_USUARIO = "INSERT INTO usuarios(nom ,apepat ,apemat ,usuario ,contra ,car ,cor ,dir ,emp ,tipusu ,ruc ,dni ,tel ,celular ) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_MENSAJERIA_BY_TIPOIMAGEN = "SELECT correoempresa , contrasenia ,url FROM vista_mensajeria WHERE tipoimagen = ?";
    private static final String SQL_SELECT_USUARIO_BY_USERNAME_PASSWORD = "SELECT idus, cor, tipusu FROM vista_usuario "
            + "WHERE usuario = ? and contra = ?";
    @Override
    public void registrarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_USUARIO);

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoPaterno());
            stmt.setString(3, usuario.getApellidoMaterno());
            stmt.setString(4, usuario.getUsuario());
            stmt.setString(5, usuario.getContrasenia());
            stmt.setString(6, usuario.getCargo());
            stmt.setString(7, usuario.getCorreo());
            stmt.setString(8, usuario.getDireccion());
            stmt.setString(9, usuario.getEmpresa());
            stmt.setString(10, usuario.getTipoUsuario());
            stmt.setString(11, usuario.getRuc());
            stmt.setString(12, usuario.getDni());
            stmt.setString(13, usuario.getTelefono());
            stmt.setString(14, usuario.getCelular());
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
            Conexion.close(conn);
        }
    }

    @Override
    public Correo generarEmail(String correoCliente, String username, String contraseniaCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Correo correo = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_MENSAJERIA_BY_TIPOIMAGEN);
            stmt.setString(1, TipoImagen.REGISTRO.getTipo());
            
            rs = stmt.executeQuery();
            String correoEmpresa="";
            String contraseniaEmpresa="";
            String urlImagen="";
            
            if(rs.next()){
                correoEmpresa = rs.getString("correoempresa");
                contraseniaEmpresa = rs.getString("contrasenia");
                urlImagen = rs.getString("url");
            }
            
            String subtitulo = TipoImagen.REGISTRO.getSubtitulo();
            String header = EmailGenerator.getHeaders(urlImagen);
            String[] registerMailTexts = new String[]{
                "Enhorabuena, te has registrado con éxito en la aplicación",
                "-------------------------------------------",
                "   Tu usuario es: " + username,
                "   Tu contraseña es: " + contraseniaCliente,
                "-------------------------------------------",
                "Esperamos que disfrutes mucho de nuestro sitio!"
            };

            String body = EmailGenerator.getBody(subtitulo, registerMailTexts);
            
            correo = new Correo(correoEmpresa, contraseniaEmpresa, correoCliente, urlImagen, header, subtitulo, body);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return correo;
    }

    @Override
    public Usuario validarUsuario(String username, String contraseniaHash) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_USUARIO_BY_USERNAME_PASSWORD);
            stmt.setString(1, username);
            stmt.setString(2, contraseniaHash);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                int idUsuario = rs.getInt("idus");
                String correo = rs.getString("cor");
                String tipoUsuario = rs.getString("tipusu");
                usuario = new Usuario(idUsuario, username, correo, tipoUsuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

}
