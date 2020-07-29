package constantes;

public enum MensajeError {
    
    ENVIO_CORREO("No se pudo enviar al correo");
    
    private final String mensaje;
    
    private MensajeError(String mensaje){
        this.mensaje = mensaje;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }
    
}
