package datos;

import dominio.Correo;
import dominio.Usuario;

public interface UsuarioDao {
    
    void registrarUsuario(Usuario usuario);
    Correo generarEmail(String correoCliente, String username, String contraseniaCliente);
    Usuario validarUsuario(String username, String contraseniaHash);
    Usuario listarUsuarios(int idUsuario);
    
}
