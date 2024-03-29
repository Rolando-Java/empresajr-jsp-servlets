package dominio;

public class MedidaAtomica {

    private int idMedidaAtomica;
    private String descripcion;

    public MedidaAtomica() {

    }

    public MedidaAtomica(int idMedidaAtomica, String descripcion) {
        this.idMedidaAtomica = idMedidaAtomica;
        this.descripcion = descripcion;
    }

    public int getIdMedidaAtomica() {
        return idMedidaAtomica;
    }

    public void setIdMedidaAtomica(int idMedidaAtomica) {
        this.idMedidaAtomica = idMedidaAtomica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MedidaAtomica{" + "idMedidaAtomica=" + idMedidaAtomica + ", descripcion=" + descripcion + '}';
    }

}
