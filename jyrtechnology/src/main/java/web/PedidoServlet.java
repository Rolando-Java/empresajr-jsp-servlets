package web;

import constantes.EstadoPedido;
import constantes.MensajeAlertJS;
import constantes.PaginaJSP;
import constantes.TipoMedidaAtomica;
import constantes.TipoTalla;
import datos.*;
import dominio.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            if (!sesion.isNew()) {
                String accion = request.getParameter("accion");
                if (accion != null) {
                    switch (accion) {
                        case "listarpedidosByUsuario":
                            //se remueve el detalle del pedido y el detalle del pedido general
                            sesion.removeAttribute("detallePedido");
                            sesion.removeAttribute("detallePedidoGeneral");
                            listarPedidosByUsuario(request, response, sesion);
                            response.sendRedirect(PaginaJSP.LISTADO_PEDIDOS.getPagina());
                            break;
                        case "ingresarFichaTecnica":
                            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
                            request.setAttribute("idPedido", idPedido);
                            request.getRequestDispatcher(PaginaJSP.REGISTRAR_FICHATECNICA.getPagina()).forward(request, response);
                            break;
                        case "verSeguimiento":
                            seguimientoDelPedido(request, response, sesion);
                            response.sendRedirect(PaginaJSP.SEGUIMIENTO_PEDIDO.getPagina());
                            break;
                        case "registrarDetallePedido":
                            //se remueve el detalle del pedido y el detalle del pedido general
                            sesion.removeAttribute("detallePedido");
                            sesion.removeAttribute("detallePedidoGeneral");
                            listarPrendas(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REGISTRAR_PEDIDO.getPagina());
                            break;
                        case "eliminarDetallePedido":
                            listarPrendas(request, response, sesion);
                            eliminarDetallePedido(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REGISTRAR_PEDIDO.getPagina());
                            break;
                        case "registrarPedido":
                            listarPrendas(request, response, sesion);
                            registrarPedido(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REGISTRAR_PEDIDO.getPagina());
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
                            listarPedidosByUsuario(request, response, sesion);
                            response.sendRedirect(PaginaJSP.LISTADO_PEDIDOS.getPagina());
                            break;
                        case "ingresarFichaTecnica":
                            ingresarFichaTecnica(request, response, sesion);
                            listarPedidosByUsuario(request, response, sesion);
                            response.sendRedirect(PaginaJSP.LISTADO_PEDIDOS.getPagina());
                            break;
                        case "registrarDetallePedido":
                            listarPrendas(request, response, sesion);
                            agregarDetallePedido(request, response, sesion);
                            response.sendRedirect(PaginaJSP.REGISTRAR_PEDIDO.getPagina());
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

    private void listarPedidosByUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
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

    private void ingresarFichaTecnica(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        int cantidadS = Integer.parseInt(request.getParameter("cantidadS"));
        int cantidadM = Integer.parseInt(request.getParameter("cantidadM"));
        int cantidadL = Integer.parseInt(request.getParameter("cantidadL"));
        int cantidadXL = Integer.parseInt(request.getParameter("cantidadXL"));
        int cantidadXXL = Integer.parseInt(request.getParameter("cantidadXXL"));
        String composicion = request.getParameter("composicion").toLowerCase();
        String color = request.getParameter("color").toLowerCase();
        String disenio = request.getParameter("disenio").toLowerCase();
        String bordado = request.getParameter("bordado").toLowerCase();
        String estampado = request.getParameter("estampado").toLowerCase();
        String tipoCuello = request.getParameter("tipoCuello").toLowerCase();
        String[] etiquetasString = request.getParameterValues("etiqueta");
        String medidaPS = request.getParameter("medidaPS").toLowerCase();
        String medidaPM = request.getParameter("medidaPM").toLowerCase();
        String medidaPL = request.getParameter("medidaPL").toLowerCase();
        String medidaPXL = request.getParameter("medidaPXL").toLowerCase();
        String medidaPXXL = request.getParameter("medidaPXXL").toLowerCase();
        String medidaCiS = request.getParameter("medidaCiS").toLowerCase();
        String medidaCiM = request.getParameter("medidaCiM").toLowerCase();
        String medidaCiL = request.getParameter("medidaCiL").toLowerCase();
        String medidaCiXL = request.getParameter("medidaCiXL").toLowerCase();
        String medidaCiXXL = request.getParameter("medidaCiXXL").toLowerCase();
        String medidaCaS = request.getParameter("medidaCaS").toLowerCase();
        String medidaCaM = request.getParameter("medidaCaM").toLowerCase();
        String medidaCaL = request.getParameter("medidaCaL").toLowerCase();
        String medidaCaXL = request.getParameter("medidaCaXL").toLowerCase();
        String medidaCaXXL = request.getParameter("medidaCaXXL").toLowerCase();
        String medidaMBS = request.getParameter("medidaMBS").toLowerCase();
        String medidaMBM = request.getParameter("medidaMBM").toLowerCase();
        String medidaMBL = request.getParameter("medidaMBL").toLowerCase();
        String medidaMBXL = request.getParameter("medidaMBXL").toLowerCase();
        String medidaMBXXL = request.getParameter("medidaMBXXL").toLowerCase();
        String medidaMLS = request.getParameter("medidaMLS").toLowerCase();
        String medidaMLM = request.getParameter("medidaMLM").toLowerCase();
        String medidaMLL = request.getParameter("medidaMLL").toLowerCase();
        String medidaMLXL = request.getParameter("medidaMLXL").toLowerCase();
        String medidaMLXXL = request.getParameter("medidaMLXXL").toLowerCase();
        String medidaEAS = request.getParameter("medidaEAS").toLowerCase();
        String medidaEAM = request.getParameter("medidaEAM").toLowerCase();
        String medidaEAL = request.getParameter("medidaEAL").toLowerCase();
        String medidaEAXL = request.getParameter("medidaEAXL").toLowerCase();
        String medidaEAXXL = request.getParameter("medidaEAXXL").toLowerCase();
        String comentario = request.getParameter("comentario").toLowerCase();

        FichaTecnicaDao fichaTecnicaJDBC = new FichaTecnicaDaoJDBC();

        //Se ingresa la ficha tecnica y se obtiene el id generado
        FichaTecnica fichaTecnica = new FichaTecnica(composicion, color, disenio, bordado,
                estampado, tipoCuello, comentario);
        int idFichaTecnica = fichaTecnicaJDBC.ingresarFichaTecnica(idPedido, fichaTecnica);

        fichaTecnica.setIdFichaTecnica(idFichaTecnica);

        //se declara las variables que se usaran para registrar los datos en el objeto de la ficha tecnica
        List<FichaTecnicaEtiqueta> fichaTecnicaEtiquetas = null;
        FichaTecnicaEtiqueta fichaTecnicaEtiqueta = null;
        Etiqueta etiqueta = null;
        TipoTalla[] tipoTallas = TipoTalla.values();
        TipoMedidaAtomica[] tipoMedidaAtomicas = TipoMedidaAtomica.values();
        List<Talla> tallas = null;
        Talla talla = null;
        List<TallaMedidaAtomica> tallaMedidaAtomicas = null;
        TallaMedidaAtomica tallaMedidaAtomica = null;
        MedidaAtomica medidaAtomica = null;
        int[] cantidades = {cantidadS, cantidadM, cantidadL, cantidadXL, cantidadXXL};
        String[][] medidas = {
            {medidaPS, medidaCiS, medidaCaS, medidaMBS, medidaMLS, medidaEAS},
            {medidaPM, medidaCiM, medidaCaM, medidaMBM, medidaMLM, medidaEAM},
            {medidaPL, medidaCiL, medidaCaL, medidaMBL, medidaMLL, medidaEAL},
            {medidaPXL, medidaCiXL, medidaCaXL, medidaMBXL, medidaMLXL, medidaEAXL},
            {medidaPXXL, medidaCiXXL, medidaCaXXL, medidaMBXXL, medidaMLXXL, medidaEAXXL}
        };

        //Generando lista de etiquetas
        fichaTecnicaEtiquetas = new ArrayList<FichaTecnicaEtiqueta>();
        for (String idEtiqueta : etiquetasString) {
            etiqueta = new Etiqueta(Integer.parseInt(idEtiqueta));
            fichaTecnicaEtiqueta = new FichaTecnicaEtiqueta(etiqueta);
            fichaTecnicaEtiquetas.add(fichaTecnicaEtiqueta);
        }
        fichaTecnica.setFichaTecnicaEtiquetas(fichaTecnicaEtiquetas);

        //Generando lista de tallas
        tallas = new ArrayList<Talla>();
        for (int i = 0; i < medidas.length; i++) {
            for (TipoTalla tipoTalla : tipoTallas) {
                if (tipoTalla.getId() == (i + 1)) {
                    talla = new Talla(tipoTalla.getId(), tipoTalla.getTalla(), cantidades[i]);
                    tallas.add(talla);
                    break;
                }
            }
        }
        fichaTecnica.setTallas(tallas);

        //Generando medidas atomicas con su respectiva talla
        for (int i = 0; i < medidas.length; i++) {
            tallaMedidaAtomicas = new ArrayList<TallaMedidaAtomica>();
            for (int j = 0; j < medidas[i].length; j++) {
                for (TipoMedidaAtomica tipoMedidaAtomica : tipoMedidaAtomicas) {
                    if (tipoMedidaAtomica.getId() == (j + 1)) {
                        medidaAtomica = new MedidaAtomica(tipoMedidaAtomica.getId(), tipoMedidaAtomica.getMedida());
                        tallaMedidaAtomica = new TallaMedidaAtomica(medidaAtomica, medidas[i][j]);
                        tallaMedidaAtomicas.add(tallaMedidaAtomica);
                        break;
                    }
                }
            }
            for (Talla tallaOBJ : tallas) {
                if (tallaOBJ.getIdTalla() == (i + 1)) {
                    tallaOBJ.setTallaMedidaAtomicas(tallaMedidaAtomicas);
                    break;
                }
            }
        }

        //se registra el detalle de la ficha tecnica
        fichaTecnicaJDBC.registrarDetalleFichaTecnica(fichaTecnica);

        //Se manda el mensaje de alert a la pagina de mis pedidos
        AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.INGRESAR_FICHATECNICA.getMensaje());
        sesion.setAttribute("mensajeAlert", alertJS);
    }

    private void seguimientoDelPedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        List<Pedido> pedidos = (List<Pedido>) sesion.getAttribute("pedidos");

        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                sesion.setAttribute("pedido", pedido);
                break;
            }
        }
    }

    private void listarPrendas(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        PedidoDao pedidoJDBC = new PedidoDaoJDBC();
        List<Prenda> prendas = pedidoJDBC.listarPrendas();
        sesion.setAttribute("prendas", prendas);
    }

    private void agregarDetallePedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        int idPrenda = Integer.parseInt(request.getParameter("tipoPrenda"));
        int cantidadPrendas = Integer.parseInt(request.getParameter("cantidadPrendas"));

        //se declaran las variables a utilizar
        List<DetallePedido> detallePedidoList = null;
        DetallePedido detallePedido = null;
        List<Prenda> prendas = null;
        Prenda prenda = null;

        /*
        Se valida que exista una lista para el detalle del pedido, 
        sino se crea una lista para el detalle del pedido
         */
        detallePedidoList = (List<DetallePedido>) sesion.getAttribute("detallePedido");
        if (detallePedidoList == null) {
            detallePedidoList = new ArrayList<DetallePedido>();
        }

        /*
        Se agrega el detalle a la lista del detalle del pedido
         */
        //primero se obtiene la prenda escogida
        prendas = (List<Prenda>) sesion.getAttribute("prendas");
        for (Prenda prendaObj : prendas) {
            if (prendaObj.getIdPrenda() == idPrenda) {
                prenda = prendaObj;
            }
        }
        //segundo se obtiene el subtotal del precio de la prenda con la cantidad de prendas
        BigDecimal subTotal = prenda.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidadPrendas));
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);

        //tercero se instancia un detalle de pedido y se agrega  a la lista del detalle de pedido
        detallePedido = new DetallePedido(prenda, subTotal, cantidadPrendas);
        detallePedidoList.add(detallePedido);
        sesion.setAttribute("detallePedido", detallePedidoList);

        /*
        Se obtiene el detalle del pedido en general
         */
        generarDetallePedidoGeneral(request, response, sesion);
    }

    private void eliminarDetallePedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
        List<DetallePedido> detallePedidoList = (List<DetallePedido>) sesion.getAttribute("detallePedido");

        //se limina el detalle del pedido de la lista de detalle del pedido
        detallePedidoList.remove(idDetalle);
        /*
        Se obtiene el detalle del pedido en general
         */
        generarDetallePedidoGeneral(request, response, sesion);
    }

    private void generarDetallePedidoGeneral(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException {
        Pedido pedido = null;
        List<DetallePedido> detallePedidoList = (List<DetallePedido>) sesion.getAttribute("detallePedido");

        //se valida que la lista del detalle del pedido no esta vacia
        if (!detallePedidoList.isEmpty()) {
            //Primero se obtiene fecha y hora del pedido
            SimpleDateFormat fechaFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat horaFormat = new SimpleDateFormat("hh:mm:ss");
            Calendar cl = GregorianCalendar.getInstance();
            cl.setLenient(false);
            Date fechaHoraActual = cl.getTime();

            String fechaActual = fechaFormat.format(fechaHoraActual);
            String horaActual = horaFormat.format(fechaHoraActual);

            //Segundo se obtiene la cantidad total de prendas solicitadas y el total a pagar
            int cantidadPrendasTotal = 0;
            BigDecimal total = BigDecimal.ZERO;
            for (DetallePedido detallePedidoObj : detallePedidoList) {
                cantidadPrendasTotal += detallePedidoObj.getCantidadPrendas();
                total = total.add(detallePedidoObj.getSubTotal());
            }
            total = total.setScale(2, RoundingMode.HALF_UP);

            //Tercero se genera una instancia del pedido y se le agrega la lista con el detalle del pedido
            pedido = new Pedido(fechaActual, horaActual, cantidadPrendasTotal, total, detallePedidoList);
        }

        sesion.setAttribute("detallePedidoGeneral", pedido);
    }

    private void registrarPedido(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws ServletException, IOException, NullPointerException {
        Pedido pedido = (Pedido) sesion.getAttribute("detallePedidoGeneral");

        if (pedido != null) {
            //se aniade el usuario al pedido
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            pedido.setUsuario(usuario);

            //se aniade le estado del pedido
            pedido.setEstado(EstadoPedido.EN_ESPERA.getEstado());

            /*
            se suma los subtototales y cantidad de pendas del detalle del pedido en caso
            se repita la prenda
             */
            List<DetallePedido> detallePedidoListObtenido = pedido.getDetallePedidos().stream().map((t) -> {
                return t.getPrenda().getIdPrenda();
            }).distinct().map((t) -> {
                //se obtiene el subtotal
                double subTotal = pedido.getDetallePedidos().stream().filter((f) -> {
                    return f.getPrenda().getIdPrenda() == t.intValue();
                }).mapToDouble((d) -> d.getSubTotal().doubleValue()).sum();
                //se obtiene la cantidad de prendas
                int cantidadPrendas = pedido.getDetallePedidos().stream()
                        .filter((f) -> f.getPrenda().getIdPrenda() == t.intValue())
                        .mapToInt(DetallePedido::getCantidadPrendas).sum();
                //se obtiene la prenda
                Prenda prenda = pedido.getDetallePedidos().stream()
                        .filter((f) -> f.getPrenda().getIdPrenda() == t.intValue())
                        .map(DetallePedido::getPrenda).findFirst()
                        .orElseThrow(() -> new NullPointerException());
                return new DetallePedido(prenda, BigDecimal.valueOf(subTotal), cantidadPrendas);
            }).sorted((x, y) -> Integer.valueOf(x.getPrenda().getIdPrenda()) 
                .compareTo(Integer.valueOf(y.getPrenda().getIdPrenda())))
                .collect(Collectors.toList());
            
            pedido.setDetallePedidos(detallePedidoListObtenido);

            //se registra el pedido
            PedidoDao pedidoJDBC = new PedidoDaoJDBC();
            pedidoJDBC.registrarPedido(pedido);

            //se remueve el detalle del pedido y el detalle del pedido general
            sesion.removeAttribute("detallePedido");
            sesion.removeAttribute("detallePedidoGeneral");

            //se describe el mensaje de alerta
            AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.INGRESAR_PEDIDO.getMensaje());
            sesion.setAttribute("mensajeAlert", alertJS);
        } else {
            //se describe el mensaje de alerta
            AlertJS alertJS = new AlertJS(Boolean.TRUE, MensajeAlertJS.INGRESAR_PEDIDO_VACIO.getMensaje());
            sesion.setAttribute("mensajeAlert", alertJS);
        }

    }

}
