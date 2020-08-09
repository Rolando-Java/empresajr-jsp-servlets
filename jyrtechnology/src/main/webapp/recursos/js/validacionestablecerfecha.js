function validarEstablecerFecha(form){

    let validacion = true;
    const estadoProduccion = form.estadoProduccion;

    if(estadoProduccion.value === 'produccioniniciada'){
        validacion = false;

        Swal.fire({
            icon: 'error',
            text: 'No es posible ingresar este estado',
        });
    } else if(estadoProduccion.value === 'produccionterminada'){
        validacion = false;

        Swal.fire({
            icon: 'error',
            text: 'Este estado solo puede ingresarse en la seccion de avances diarios',
        });
    }

    return validacion;

}