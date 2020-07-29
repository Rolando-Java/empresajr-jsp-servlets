package util;

import dominio.Correo;
import excepciones.CorreoException;

public class UsuarioUtilitario {

    public static String crearUsername(String nombre, String apellidoPaterno, String telefono) {
        char primerCaracterNombre = nombre.charAt(0);
        String tresCifrasTelefono = telefono.substring(0, 3);
        return (primerCaracterNombre + apellidoPaterno + tresCifrasTelefono);
    }
    
    public static boolean enviarEmail(Correo correo) throws CorreoException{
        String mailText = correo.getHeader() + correo.getBody();
        return Mailer.enviarHTMLMail(correo.getCorreoEmpresa(), 
                correo.getContraseniaEmpresa(), correo.getCorreoCliente(), correo.getSubtitle(), mailText);
    }
    
}
