package constantes;

public enum PaginaJSP {

    ERROR("WEB-INF/paginas/comunes/error.jsp"),
    SESION("sesion.jsp"),
    REGISTRO("registro.jsp"),
    LISTADO_PEDIDOS("listadodepedidos.jsp"),
    SOLICITUD_PEDIDOS("solicituddepedidos.jsp"),
    REGISTRAR_FICHATECNICA("registrodefichatecnica.jsp"),
    REGISTRAR_PEDIDO("registrodepedido.jsp"),
    SEGUIMIENTO_PEDIDO("seguimientodepedidos.jsp"),
    ESTABLECER_FECHA_PRODUCCION("establecerfechadeproduccion.jsp");

    private final String pagina;

    private PaginaJSP(String pagina) {
        this.pagina = pagina;
    }
    
    public String getPagina(){
        return this.pagina;
    }

}
