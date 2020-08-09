package constantes;

public enum MensajeAlertJS {
    
    REGISTRO("Se ha registrado con exito. Los datos de su cuenta fueron enviados a su correo"),
    EXPIRACION("Demasiado tiempo inactivo. Su sesión ha expirado"),
    INGRESAR_FICHATECNICA("La ficha tecnica se ingreso correctamente"),
    INGRESAR_PEDIDO("El pedido se ingreso correctamente"),
    INGRESAR_PEDIDO_VACIO("Lo sentimos. No hay pedido que registrar"),
    SOLICTUD_PEDIDO("El mensaje se envio correctamente");
    
    private final String mensaje;
    
    private MensajeAlertJS(String mensaje){
        this.mensaje = mensaje;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }

}
