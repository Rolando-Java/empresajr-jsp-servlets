package web;

import constantes.EstadoPedido;
import constantes.MensajeAlertJS;
import constantes.PaginaJSP;
import datos.PedidoDao;
import datos.PedidoDaoJDBC;
import dominio.*;
import excepciones.CorreoException;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.UsuarioUtilitario;

@WebServlet("/SolicitudPedidoServlet")
public class SolicitudPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            if (!sesion.isNew()) {
                String accion = request.getParameter("accion");
                if (accion != null) {
                    switch (accion) {
                        case "listarPedidos":
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.SOLICITUD_PEDIDOS.getPagina());
                            break;
                    }
                }
            } else {
                AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.EXPIRACION.getMensaje());
                sesion.setAttribute("mensajeAlert", alertJS);
                response.sendRedirect(PaginaJSP.SESION.getPagina());
            }
        } catch (Exception ex) {
            request.setAttribute("excepcion", ex);
            request.getRequestDispatcher(PaginaJSP.ERROR.getPagina()).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            if (!sesion.isNew()) {
                String accion = request.getParameter("accion");
                if (accion != null) {
                    switch (accion) {
                        //esta accion es invocada por SesionServlet
                        case "iniciar":
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.SOLICITUD_PEDIDOS.getPagina());
                            break;
                        case "solicitudPedido":
                            modificarEstadoPedido(request, response, sesion);
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.SOLICITUD_PEDIDOS.getPagina());
                            break;
                    }
                }
            } else {
                AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.EXPIRACION.getMensaje());
                sesion.setAttribute("mensajeAlert", alertJS);
                response.sendRedirect(PaginaJSP.SESION.getPagina());
            }
        } catch (CorreoException ex) {
            request.setAttribute("excepcion", ex);
            request.getRequestDispatcher(PaginaJSP.ERROR.getPagina()).forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("excepcion", ex);
            request.getRequestDispatcher(PaginaJSP.ERROR.getPagina()).forward(request, response);
        }
    }

    private void listarPedidos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se obtiene los pedidos que estan en espera
        PedidoDao pedidoJDBC = new PedidoDaoJDBC();
        List<Pedido> pedidos = pedidoJDBC.listarPedidos(EstadoPedido.EN_ESPERA.getEstado());
        sesion.setAttribute("pedidos", pedidos);
    }

    private void modificarEstadoPedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException, CorreoException {
        //se declaran las variables
        String mensaje = request.getParameter("mensaje");
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String estadoPedido = request.getParameter("estadoPedido");
        Usuario usuario = null;
        
        //se obtiene el usuario
        List<Pedido> pedidos = (List<Pedido>)sesion.getAttribute("pedidos");
        for(Pedido pedidoObj : pedidos){
            if(pedidoObj.getIdPedido() == idPedido){
                usuario = pedidoObj.getUsuario();
            }
        }
        
        //se genera el email y se envia la solicitud del pedido al correo del cliente
        PedidoDao pedidoJDBC = new PedidoDaoJDBC();
        Correo correo = pedidoJDBC.generarEmailSolicitudPedido(usuario, mensaje);
        boolean enviado = UsuarioUtilitario.enviarEmail(correo);

        if (enviado) {
            //se modfica el estado del pedido
            pedidoJDBC.modificarEstadoPedido(idPedido, estadoPedido);

            //se genera el mensaje de alerta
            AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.SOLICTUD_PEDIDO.getMensaje());
            sesion.setAttribute("mensajeAlert", alertJS);
        }
    }

}
