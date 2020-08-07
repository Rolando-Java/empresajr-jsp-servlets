package constantes;

public enum EstadoProduccion {

    PRODUCCION_INICIADA("produccioniniciada"),
    PRODUCCION_PAUSA("produccionpausa"),
    PRODUCCION_AVANCE("produccionavance"),
    PRODUCCION_TERMINADA("produccionterminada"),
    PRODUCCION_TERMINADA_ALMACEN("produccionterminadaalmacen"),
    ALMACEN("almacen"),
    ENTREGADO("entregado");

    private final String estado;

    private EstadoProduccion(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}
