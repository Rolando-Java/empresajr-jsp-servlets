package dominio;

import java.util.List;

public class FichaTecnica {

    private int idFichaTecnica;
    private String composicion;
    private String color;
    private String disenio;
    private String bordado;
    private String estampado;
    private String tipoCuello;
    private String comentario;
    private List<Talla> tallas;
    private List<FichaTecnicaEtiqueta> fichaTecnicaEtiquetas;

    public FichaTecnica() {

    }

    public FichaTecnica(int idFichaTecnica) {
        this.idFichaTecnica = idFichaTecnica;
    }

    public int getIdFichaTecnica() {
        return idFichaTecnica;
    }

    public void setIdFichaTecnica(int idFichaTecnica) {
        this.idFichaTecnica = idFichaTecnica;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDisenio() {
        return disenio;
    }

    public void setDisenio(String disenio) {
        this.disenio = disenio;
    }

    public String getBordado() {
        return bordado;
    }

    public void setBordado(String bordado) {
        this.bordado = bordado;
    }

    public String getEstampado() {
        return estampado;
    }

    public void setEstampado(String estampado) {
        this.estampado = estampado;
    }

    public String getTipoCuello() {
        return tipoCuello;
    }

    public void setTipoCuello(String tipoCuello) {
        this.tipoCuello = tipoCuello;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Talla> getTallas() {
        return tallas;
    }

    public void setTallas(List<Talla> tallas) {
        this.tallas = tallas;
    }

    public List<FichaTecnicaEtiqueta> getFichaTecnicaEtiquetas() {
        return fichaTecnicaEtiquetas;
    }

    public void setFichaTecnicaEtiquetas(List<FichaTecnicaEtiqueta> fichaTecnicaEtiquetas) {
        this.fichaTecnicaEtiquetas = fichaTecnicaEtiquetas;
    }

    @Override
    public String toString() {
        return "FichaTecnica{" + "idFichaTecnica=" + idFichaTecnica + ", composicion=" + composicion + ", color=" + color + ", disenio=" + disenio + ", bordado=" + bordado + ", estampado=" + estampado + ", tipoCuello=" + tipoCuello + ", comentario=" + comentario + ", tallas=" + tallas + ", fichaTecnicaEtiquetas=" + fichaTecnicaEtiquetas + '}';
    }

}
