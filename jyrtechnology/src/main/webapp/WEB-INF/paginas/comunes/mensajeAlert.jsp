<script>
    var estado = ${mensajeAlert.estado};
    if (estado) {
        alert("${mensajeAlert.mensaje}");
    }
    <jsp:scriptlet>
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("mensajeAlert");
    </jsp:scriptlet>
</script>