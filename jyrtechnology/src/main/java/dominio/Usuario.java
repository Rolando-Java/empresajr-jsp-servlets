package dominio;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String usuario;
    private String contrasenia;
    private String cargo;
    private String correo;
    private String direccion;
    private String empresa;
    private String tipoUsuario;
    private String ruc;
    private String dni;
    private String telefono;
    private String celular;

    public Usuario() {
        
    }
    
    public Usuario(int idUsuario, String username, String correoCliente, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.usuario = username;
        this.correo = correoCliente;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String usuario, String contrasenia, String cargo, String correo, String direccion, String empresa, String tipoUsuario, String ruc, String dni, String telefono, String celular) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.cargo = cargo;
        this.correo = correo;
        this.direccion = direccion;
        this.empresa = empresa;
        this.tipoUsuario = tipoUsuario;
        this.ruc = ruc;
        this.dni = dni;
        this.telefono = telefono;
        this.celular = celular;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", cargo=" + cargo + ", correo=" + correo + ", direccion=" + direccion + ", empresa=" + empresa + ", tipoUsuario=" + tipoUsuario + ", ruc=" + ruc + ", dni=" + dni + ", telefono=" + telefono + ", celular=" + celular + '}';
    }

}
