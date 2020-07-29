package dominio;

public class Correo {

    private String correoEmpresa;
    private String contraseniaEmpresa;
    private String correoCliente;
    private String urlImagen;
    private String header;
    private String subtitle;
    private String body;

    public Correo() {

    }

    public Correo(String correoEmpresa, String contraseniaEmpresa, String correoCliente, String urlImagen, String header, String subtitle, String body) {
        this.correoEmpresa = correoEmpresa;
        this.contraseniaEmpresa = contraseniaEmpresa;
        this.correoCliente = correoCliente;
        this.urlImagen = urlImagen;
        this.header = header;
        this.subtitle = subtitle;
        this.body = body;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getContraseniaEmpresa() {
        return contraseniaEmpresa;
    }

    public void setContraseniaEmpresa(String contraseniaEmpresa) {
        this.contraseniaEmpresa = contraseniaEmpresa;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Correo{" + "correoEmpresa=" + correoEmpresa + ", contraseniaEmpresa=" + contraseniaEmpresa + ", correoCliente=" + correoCliente + ", urlImagen=" + urlImagen + ", header=" + header + ", subtitle=" + subtitle + ", body=" + body + '}';
    }

}
