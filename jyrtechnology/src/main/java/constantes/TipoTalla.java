package constantes;

public enum TipoTalla {

    TALLA_S(1, "s"),
    TALLA_M(2, "m"),
    TALLA_L(3, "l"),
    TALLA_XL(4, "xl"),
    TALLA_XXL(5, "xxl");

    private final int id;
    private final String talla;

    private TipoTalla(int id, String talla) {
        this.id = id;
        this.talla = talla;
    }

    public int getId() {
        return this.id;
    }

    public String getTalla() {
        return this.talla;
    }

}
