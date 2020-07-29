package dominio;

public class AlertJS {
    
    private boolean estado;
    private String mensaje;
    
    public AlertJS(){
        
    }
    
    public AlertJS(boolean estado, String mensaje){
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "AlertJS{" + "estado=" + estado + ", mensaje=" + mensaje + '}';
    }
    
    
    
}
