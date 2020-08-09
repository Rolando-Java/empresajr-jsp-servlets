function validarSolicitudPedido(form){

    let validacion = true;
    const mensaje = form.mensaje;

    if(mensaje.value.length === 0 || isEmpty(mensaje.value)){
        Swal.fire({
            icon: 'error',
            text: 'Ingrese un mensaje',
        });
        
        validacion = false;
    }

    return validacion;

}

function isEmpty(texto){
    return /^\s+$/.test(texto);
}