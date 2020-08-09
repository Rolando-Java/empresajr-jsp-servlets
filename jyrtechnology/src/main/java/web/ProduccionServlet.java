package web;

import constantes.*;
import datos.ProduccionDao;
import datos.ProduccionDaoJDBC;
import dominio.AlertJS;
import dominio.Pedido;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProduccionServlet")
public class ProduccionServlet extends HttpServlet {

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
                            response.sendRedirect(PaginaJSP.ESTABLECER_FECHA_PRODUCCION.getPagina());
                            break;
                        case "ingresaravancediario":
                            listarPedidosEnProduccion(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_AVANCE_DIARIO.getPagina());
                            break;
                        case "concluirproduccion":
                            concluirProduccion(request, response, sesion);
                            listarPedidosEnProduccion(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_AVANCE_DIARIO.getPagina());
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
                            response.sendRedirect(PaginaJSP.ESTABLECER_FECHA_PRODUCCION.getPagina());
                            break;
                        case "establecerfechaproduccion":
                            registrarProduccion(request, response, sesion);
                            listarPedidos(request, response, sesion);
                            response.sendRedirect(PaginaJSP.ESTABLECER_FECHA_PRODUCCION.getPagina());
                            break;
                        case "ingresaravancediario":
                            registrarAvanceDiario(request, response, sesion);
                            listarPedidosEnProduccion(request, response, sesion);
                            response.sendRedirect(PaginaJSP.INGRESAR_AVANCE_DIARIO.getPagina());
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

    private void registrarProduccion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException, ParseException {
        //se declaran las variables
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String estadoProduccion = request.getParameter("estadoProduccion");
        String fechaString = request.getParameter("fecha");

        //parseando la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaString);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        fechaString = sdf.format(fecha);

        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        produccionJDBC.registrarProduccion(idPedido, estadoProduccion, fechaString);

        /*
        se crea un atributo bandera para evitar conflictos a la hora de listar pedidos, 
        una vez se halla registrado la produccion
         */
        request.setAttribute("produccionRegistrada", Boolean.TRUE);
    }

    private void registrarAvanceDiario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se decalran las variables
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        int subTotal = Integer.parseInt(request.getParameter("subTotal"));
        int cantidadAvance = Integer.parseInt(request.getParameter("cantidadAvance"));
        String fechaActual = null;

        //se obtiene la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);
        fechaActual = sdf.format(cl.getTime());
        
        //se obtiene el total avanzado
        int total = subTotal + cantidadAvance;
        
        //se registra el avance diario con estado en produccion avance
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        produccionJDBC.registrarProduccion(idPedido, EstadoProduccion.PRODUCCION_AVANCE.getEstado(), fechaActual, total);
    }

    private void listarPedidos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException, ParseException {
        String estadoProduccion = request.getParameter("estadoProduccion");
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        List<Pedido> pedidos = null;

        //se valida que no se este registrando la produccion
        boolean produccionRegistrada = ((Boolean) request.getAttribute("produccionRegistrada") != null) ? (Boolean) request.getAttribute("produccionRegistrada") : Boolean.FALSE;

        if (estadoProduccion != null && !produccionRegistrada) {
            /*
            se obtiene los pedidos que estan aceptado, tienen ficha tecnica y un estado de produccion
            en especifico
             */
            pedidos = produccionJDBC.listarPedidos(EstadoPedido.ACEPTADO.getEstado(), estadoProduccion);
        } else {
            /*
            se obtiene los pedidos que estan aceptado, tienen ficha tecnica y un estado de produccion
            como produccioniniciada o produccionpausa o produccionavance o produccionterminada
             */
            pedidos = produccionJDBC.listarPedidos(EstadoPedido.ACEPTADO.getEstado());
        }

        //obtener las fechas de entrega estimada
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);

        for (Pedido pedido : pedidos) {
            cl.setTime(sdf.parse(pedido.getFecha()));
            cl.add(Calendar.DAY_OF_MONTH, 12);
            pedido.setFecha(sdf.format(cl.getTime()));
        }
        sesion.setAttribute("pedidos", pedidos);
    }

    private void listarPedidosEnProduccion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException, ParseException {
        //se lista los pedidos aceptado con estado de produccion avance
        List<Pedido> pedidos = null;
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        pedidos = produccionJDBC.listarPedidos(EstadoPedido.ACEPTADO.getEstado(), EstadoProduccion.PRODUCCION_AVANCE.getEstado());
        
        //obtener las fechas de entrega estimada
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);

        for (Pedido pedido : pedidos) {
            cl.setTime(sdf.parse(pedido.getFecha()));
            cl.add(Calendar.DAY_OF_MONTH, 12);
            pedido.setFecha(sdf.format(cl.getTime()));
        }
        sesion.setAttribute("pedidos", pedidos);
    }

    private void concluirProduccion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        //se decalran las variables
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String fechaActual = null;
        
        //se obtiene la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cl = GregorianCalendar.getInstance();
        cl.setLenient(false);
        fechaActual = sdf.format(cl.getTime());
        
        //se registra la produccion con estado de produccionterminada
        ProduccionDao produccionJDBC = new ProduccionDaoJDBC();
        produccionJDBC.registrarProduccion(idPedido, EstadoProduccion.PRODUCCION_TERMINADA.getEstado(), fechaActual);
    }

}
