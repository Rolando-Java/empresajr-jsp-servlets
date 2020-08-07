package constantes;

public enum TipoMedidaAtomica {

    PECHO(1, "pecho"),
    CINTURA(2, "cintura"),
    CADERA(3, "cadera"),
    MANGA_BASTA(4, "manga(basta)"),
    MANGA_LARGA(5, "manga(larga)"),
    ESPALDA_ALTO(6, "espalda(alto)");

    private final int id;
    private final String medida;

    private TipoMedidaAtomica(int id, String medida) {
        this.id = id;
        this.medida = medida;
    }

    public int getId() {
        return this.id;
    }

    public String getMedida() {
        return this.medida;
    }

}
