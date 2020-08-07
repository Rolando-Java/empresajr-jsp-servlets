package constantes;

public enum TipoUsuario {
    
    CLIENTE("cliente"),
    ADMINISTRADOR("administrador"),
    JEFE_PRODUCCION("jefeproduccion"),
    JEFE_ALMACEN("jefealmacen");
    
    private final String tipo;
    
    private TipoUsuario(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
}
