package dominio;

import java.math.BigDecimal;

public class Prenda {

    private int idPrenda;
    private String tipoPrenda;
    private BigDecimal precioUnitario;

    public Prenda() {

    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Prenda{" + "idPrenda=" + idPrenda + ", tipoPrenda=" + tipoPrenda + ", precioUnitario=" + precioUnitario + '}';
    }

}
