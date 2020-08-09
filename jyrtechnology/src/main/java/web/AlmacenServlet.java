package web;

import constantes.EstadoPedido;
import constantes.EstadoProduccion;
import constantes.MensajeAlertJS;
import constantes.PaginaJSP;
import datos.ProduccionDao;
import datos.ProduccionDaoJDBC;
import dominio.*;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AlmacenServlet")
public class AlmacenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            if (!sesion.isNew()) {
                String accion = request.getParameter("accion");
                if (accion != null) {
                    switch (accion) {
                        case "listarPedidosTerminados":
                            listarPedidosTerminados(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_PEDIDOS_TERMINADOS.getPagina());
                            break;
                        case "ingresarPedidoTerminado":
                            ingresarPedidoTerminado(request, response, sesion);
                            listarPedidosTerminados(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_PEDIDOS_TERMINADOS.getPagina());
                            break;
                        case "listarPedidosEnAlmacen":
                            listarPedidosEnAlmacen(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REPORTE_ALMACEN.getPagina());
                            break;
                        case "entregarPedido":
                            entregarPedido(request, response, sesion);
                            listarPedidosEnAlmacen(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REPORTE_ALMACEN.getPagina());
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
                            listarPedidosTerminados(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_PEDIDOS_TERMINADOS.getPagina());
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

    private void listarPedidosTerminados(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se lista los pedidos aceptados con estado de produccion terminada en almacen
        List<Pedido> pedidos = null;
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        pedidos = produccionJDBC.listarPedidos(EstadoPedido.ACEPTADO.getEstado(), EstadoProduccion.PRODUCCION_TERMINADA_ALMACEN.getEstado());
        sesion.setAttribute("pedidos", pedidos);
    }

    private void listarPedidosEnAlmacen(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se lista los pedidos aceptados con estado de produccion almacen
        List<Pedido> pedidos = null;
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        pedidos = produccionJDBC.listarPedidos(EstadoPedido.ACEPTADO.getEstado(), EstadoProduccion.ALMACEN.getEstado());
        sesion.setAttribute("pedidos", pedidos);
    }

    private void ingresarPedidoTerminado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se declaran las variables
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String fechaActual = null;

        //se obtiene la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);
        fechaActual = sdf.format(cl.getTime());

        //se registra la produccion con estado de produccionterminada
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        produccionJDBC.registrarProduccion(idPedido, EstadoProduccion.ALMACEN.getEstado(), fechaActual);
    }

    private void entregarPedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se declaran las variables
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String fechaActual = null;

        //se obtiene la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);
        fechaActual = sdf.format(cl.getTime());

        //se registra la produccion con estado de produccionterminada
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        produccionJDBC.registrarProduccion(idPedido, EstadoProduccion.ENTREGADO.getEstado(), fechaActual);
    }

}
