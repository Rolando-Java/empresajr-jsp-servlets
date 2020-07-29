package web;

import constantes.MensajeAlertJS;
import constantes.PaginaJSP;
import constantes.TipoUsuario;
import datos.UsuarioDao;
import datos.UsuarioDaoJDBC;
import dominio.AlertJS;
import dominio.Correo;
import dominio.Usuario;
import excepciones.CorreoException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.codec.digest.DigestUtils;
import util.*;

@WebServlet("/SesionServlet")
public class SesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "registrar":
                    response.sendRedirect(PaginaJSP.REGISTRO.getPagina());
                    break;
                case "cerrar":
                    response.sendRedirect(PaginaJSP.SESION.getPagina());
                    break;
            }
        } else {
            response.sendRedirect(PaginaJSP.SESION.getPagina());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            if (accion != null) {
                switch (accion) {
                    case "registrar":
                        registrarUsuario(request, response);
                        response.sendRedirect(PaginaJSP.SESION.getPagina());
                        break;
                    case "iniciar":
                        Usuario usuario = validaUsuario(request, response);
                        if (usuario != null) {
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("usuario", usuario);
                            if (usuario.getTipoUsuario().equalsIgnoreCase(TipoUsuario.CLIENTE.getTipo())) {
                                request.getRequestDispatcher("PedidoServlet").forward(request, response);
                            } else if (usuario.getTipoUsuario().equalsIgnoreCase(TipoUsuario.ADMINISTRADOR.getTipo())) {
                                response.sendRedirect(PaginaJSP.SOLICITUD_PEDIDOS.getPagina());
                            }
                        } else {
                            response.sendRedirect(PaginaJSP.SESION.getPagina());
                        }
                        break;
                }
            }
        } catch (CorreoException ex) {
            request.setAttribute("excepcion", ex);
            request.getRequestDispatcher(PaginaJSP.ERROR.getPagina()).forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("excepcion", ex);
            request.getRequestDispatcher(PaginaJSP.ERROR.getPagina()).forward(request, response);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CorreoException {
        String nombre = request.getParameter("nombre").toLowerCase();
        String apellidoPaterno = request.getParameter("apellidoPaterno").toLowerCase();
        String apellidoMaterno = request.getParameter("apellidoMaterno").toLowerCase();
        String contrasenia = request.getParameter("contrasenia").toLowerCase();
        String cargo = request.getParameter("cargo").toLowerCase();
        String correo = request.getParameter("correo").toLowerCase();
        String direccion = request.getParameter("direccion").toLowerCase();
        String empresa = request.getParameter("empresa").toLowerCase();
        String ruc = request.getParameter("ruc").toLowerCase();
        String dni = request.getParameter("dni").toLowerCase();
        String telefono = request.getParameter("telefono").toLowerCase();
        String celular = request.getParameter("celular").toLowerCase();

        String username = UsuarioUtilitario.crearUsername(nombre, apellidoPaterno, telefono);
        String contraseniaHash = DigestUtils.md5Hex(contrasenia);
        String tipoUsuario = TipoUsuario.CLIENTE.getTipo();

        Usuario usuario = new Usuario(nombre, apellidoPaterno, apellidoMaterno, username, contraseniaHash, cargo, correo, direccion, empresa, tipoUsuario, ruc, dni, telefono, celular);

        UsuarioDao usuarioJDBC = new UsuarioDaoJDBC();
        Correo email = usuarioJDBC.generarEmail(correo, username, contrasenia);
        boolean enviado = UsuarioUtilitario.enviarEmail(email);

        if (enviado) {
            usuarioJDBC.registrarUsuario(usuario);
            AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.REGISTRO.getMensaje());
            HttpSession sesion = request.getSession();
            sesion.setAttribute("mensajeAlert", alertJS);
        }
    }

    private Usuario validaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        String contraseniaHash = DigestUtils.md5Hex(contrasenia);

        UsuarioDao usuarioJDBC = new UsuarioDaoJDBC();
        return usuarioJDBC.validarUsuario(username, contraseniaHash);
    }

}
