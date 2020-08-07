package dominio;

import java.util.List;

public class Talla {

    private int idTalla;
    private String talla;
    private int cantidad;
    private List<TallaMedidaAtomica> tallaMedidaAtomicas;

    public Talla() {

    }

    public Talla(int idTalla, String talla, int cantidad) {
        this.idTalla = idTalla;
        this.talla = talla;
        this.cantidad = cantidad;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<TallaMedidaAtomica> getTallaMedidaAtomicas() {
        return tallaMedidaAtomicas;
    }

    public void setTallaMedidaAtomicas(List<TallaMedidaAtomica> tallaMedidaAtomicas) {
        this.tallaMedidaAtomicas = tallaMedidaAtomicas;
    }

    @Override
    public String toString() {
        return "Talla{" + "idTalla=" + idTalla + ", talla=" + talla + ", cantidad=" + cantidad + ", tallaMedidaAtomicas=" + tallaMedidaAtomicas + '}';
    }

}
