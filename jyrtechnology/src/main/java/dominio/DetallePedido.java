package dominio;

import java.math.BigDecimal;

public class DetallePedido {
    
    private int idDetallePedido;
    private Prenda prenda;
    private BigDecimal subTotal;
    private int cantidadPrendas;
    
    public DetallePedido(){
        
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getCantidadPrendas() {
        return cantidadPrendas;
    }

    public void setCantidadPrendas(int cantidadPrendas) {
        this.cantidadPrendas = cantidadPrendas;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "idDetallePedido=" + idDetallePedido + ", prenda=" + prenda + ", subTotal=" + subTotal + ", cantidadPrendas=" + cantidadPrendas + '}';
    }
    
}
