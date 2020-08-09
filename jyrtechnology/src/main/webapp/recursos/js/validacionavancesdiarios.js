function validarAvanceDiario(form) {
    let validacion = true;
    var cantidadTotal = form.cantidadTotal.value;
    var subTotal = form.subTotal.value;
    var cantidadAvance = form.cantidadAvance.value;

    if (cantidadAvance === '' || isEmpty(cantidadAvance)) {
        Swal.fire({
            icon: 'error',
            text: 'Ingrese un cantidad de avance',
        });

        validacion = false;
    } else if (!isNumber(cantidadAvance)) {
        Swal.fire({
            icon: 'error',
            text: 'Ingrese un cantidad valida',
        });

        validacion = false;
    } else if (obtenerCantidadTotal(subTotal, cantidadAvance) > parseInt(cantidadTotal)) {
        Swal.fire({
            icon: 'error',
            text: 'La cantidad avanzada es mayor a la cantidad de prendas total',
        });

        validacion = false;
    }

    return validacion;
}

function isEmpty(texto) {
    return /^\s+$/.test(texto);
}

function isNumber(texto) {
    return /^\d+$/.test(texto);
}

function obtenerCantidadTotal(subTotal, cantidadAvance) {
    return parseInt(subTotal) + parseInt(cantidadAvance);
}