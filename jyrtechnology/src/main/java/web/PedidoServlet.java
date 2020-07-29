package web;

import constantes.MensajeAlertJS;
import constantes.PaginaJSP;
import datos.PedidoDao;
import datos.PedidoDaoJDBC;
import dominio.AlertJS;
import dominio.Pedido;
import dominio.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            if (!sesion.isNew()) {
                String accion = request.getParameter("accion");
                String estadoPedido = request.getParameter("estadoPedido");
                if (accion != null) {
                    switch (accion) {
                        case "listarpedidos":
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.LISTADO_PEDIDOS.getPagina());
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
                        case "iniciar":
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.LISTADO_PEDIDOS.getPagina());
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

    private void listarPedidos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        String estadoPedido = request.getParameter("estadoPedido");
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        PedidoDao pedidoJDBC = new PedidoDaoJDBC();
        List<Pedido> pedidos = null;
        if (estadoPedido != null) {
            pedidos = pedidoJDBC.listarPedidos(usuario.getIdUsuario(), estadoPedido);
        } else {
            pedidos = pedidoJDBC.listarPedidos(usuario.getIdUsuario());
        }
        sesion.setAttribute("pedidos", pedidos);
    }

}
