package dominio;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

    private int idPedido;
    private Usuario usuario;
    private String fecha;
    private String hora;
    private String estado;
    private int cantidadPrendasTotal;
    private BigDecimal total;
    private List<DetallePedido> detallePedidos;
    private FichaTecnica fichaTecnica;

    public Pedido() {

    }

    public Pedido(int idPedido, String fecha, String hora, String estado, int cantidadPrendasTotal, BigDecimal total, FichaTecnica fichaTecnica) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.cantidadPrendasTotal = cantidadPrendasTotal;
        this.total = total;
        this.fichaTecnica = fichaTecnica;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidadPrendasTotal() {
        return cantidadPrendasTotal;
    }

    public void setCantidadPrendasTotal(int cantidadPrendasTotal) {
        this.cantidadPrendasTotal = cantidadPrendasTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public FichaTecnica getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(FichaTecnica fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", usuario=" + usuario + ", fecha=" + fecha + ", hora=" + hora + ", estado=" + estado + ", cantidadPrendasTotal=" + cantidadPrendasTotal + ", total=" + total + ", detallePedidos=" + detallePedidos + ", fichaTecnica=" + fichaTecnica + '}';
    }

}
