package dominio;

public class FichaTecnicaEtiqueta {

    private int idFichaTecnicaEtiqueta;
    private Etiqueta etiqueta;

    public FichaTecnicaEtiqueta() {

    }

    public int getIdFichaTecnicaEtiqueta() {
        return idFichaTecnicaEtiqueta;
    }

    public void setIdFichaTecnicaEtiqueta(int idFichaTecnicaEtiqueta) {
        this.idFichaTecnicaEtiqueta = idFichaTecnicaEtiqueta;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "FichaTecnicaEtiqueta{" + "idFichaTecnicaEtiqueta=" + idFichaTecnicaEtiqueta + ", etiqueta=" + etiqueta + '}';
    }

}
