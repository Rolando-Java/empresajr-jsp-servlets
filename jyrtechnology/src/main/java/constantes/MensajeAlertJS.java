package constantes;

public enum MensajeAlertJS {
    
    REGISTRO("Se ha registrado con exito. Los datos de su cuenta fueron enviados a su correo"),
    EXPIRACION("Demasiado tiempo inactivo. Su sesión ha expirado");
    
    private final String mensaje;
    
    private MensajeAlertJS(String mensaje){
        this.mensaje = mensaje;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }

}
