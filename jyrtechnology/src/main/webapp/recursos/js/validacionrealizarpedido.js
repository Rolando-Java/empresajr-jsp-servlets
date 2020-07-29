function validarRealizarPedido(form){
    
    let validacion = true;
    const tipoPrenda = form.tipoPrenda;
    const cantidadPrendas = form.cantidadPrendas;

    if(tipoPrenda.value === 'Seleccione su tipo de prenda'){
        setErrorFor(tipoPrenda, 'Ingrese un tipo de prenda');
        validacion = false;
    } else {
        setSuccessFor(tipoPrenda);
    }

    if(cantidadPrendas.value === '' || isEmpty(cantidadPrendas.value)){
        setErrorFor(cantidadPrendas, 'Ingrese una cantidad');
        validacion = false;
    } else if(!isNumero(cantidadPrendas.value)){
        setErrorFor(cantidadPrendas, 'Ingrese una cantidad valida');
        validacion = false;
    } else if(!isCantidadPrendas(cantidadPrendas.value)){
        setErrorFor(cantidadPrendas,'Esa cantidad supera el cupo maximo')
        validacion = false;
    } else {
        setSuccessFor(cantidadPrendas);
    }

    return validacion;
}

function setErrorFor(input, message) {
    const campo = input.parentElement
    const small = campo.querySelector('small')
    campo.className = 'campo error'
    small.innerText = message
}

function setSuccessFor(input) {
    const campo = input.parentElement
    campo.className = 'campo success'
}

function isNumero(cantidadPrendas){
    return /^\d+$/.test(cantidadPrendas);
}

function isCantidadPrendas(cantidadPrendas){
    return /^[0-9]{1,8}$/.test(cantidadPrendas);
}

function isEmpty(texto){
    return /^\s+$/.test(texto);
}