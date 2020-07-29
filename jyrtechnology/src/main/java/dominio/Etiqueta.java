package dominio;

public class Etiqueta {

    private int idEtiqueta;
    private String etiqueta;

    public Etiqueta() {

    }

    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "idEtiqueta=" + idEtiqueta + ", etiqueta=" + etiqueta + '}';
    }

}
