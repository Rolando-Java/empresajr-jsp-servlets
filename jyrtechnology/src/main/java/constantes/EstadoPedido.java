package constantes;

public enum EstadoPedido {
    
    EN_ESPERA("en espera"),
    RECHAZADO("rechazado"),
    ACEPTADO("aceptado");
    
    private final String estado;
    
    private EstadoPedido(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
}
