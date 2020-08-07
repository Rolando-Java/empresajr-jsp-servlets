package dominio;

public class Produccion {

    private int idProduccion;
    private String estado;
    private String fecha;
    private int cantidadAvance;

    public Produccion() {

    }

    public Produccion(int idProduccion, String estado, String fecha, int cantidadAvance) {
        this.idProduccion = idProduccion;
        this.estado = estado;
        this.fecha = fecha;
        this.cantidadAvance = cantidadAvance;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidadAvance() {
        return cantidadAvance;
    }

    public void setCantidadAvance(int cantidadAvance) {
        this.cantidadAvance = cantidadAvance;
    }

    @Override
    public String toString() {
        return "Produccion{" + "idProduccion=" + idProduccion + ", estado=" + estado + ", fecha=" + fecha + ", cantidadAvance=" + cantidadAvance + '}';
    }

}
