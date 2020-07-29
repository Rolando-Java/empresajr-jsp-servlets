package constantes;

public enum TipoImagen {
    
    REGISTRO("registro","Registro en J&R"),
    PEDIDO("pedido","Solicitud de pedidos en J&R");
    
    private final String tipo;
    private final String subtitulo;
    
    private TipoImagen(String tipo, String subtitulo){
        this.tipo = tipo;
        this.subtitulo = subtitulo;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public String getSubtitulo(){
        return this.subtitulo;
    }
    
}
