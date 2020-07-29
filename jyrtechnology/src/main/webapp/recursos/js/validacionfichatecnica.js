function validarFichaTecnica(form){

    let validacion = true;
    const cantidadS = form.cantidadS;
    const cantidadM = form.cantidadM;
    const cantidadL = form.cantidadL;
    const cantidadXL = form.cantidadXL;
    const cantidadXXL = form.cantidadXXL;
    const composicion = form.composicion;
    const color = form.color;
    const disenio = form.disenio;
    const bordado = form.bordado;
    const estampado = form.estampado;
    const tipoCuello = form.tipoCuello;
    const etiquetas = form.etiqueta;
    const medidaPS = form.medidaPS;
    const medidaPM = form.medidaPM;
    const medidaPL = form.medidaPL;
    const medidaPXL = form.medidaPXL;
    const medidaPXXL = form.medidaPXXL;
    const medidaCiS = form.medidaCiS;
    const medidaCiM = form.medidaCiM;
    const medidaCiL = form.medidaCiL;
    const medidaCiXL = form.medidaCiXL;
    const medidaCiXXL = form.medidaCiXXL;
    const medidaCaS = form.medidaCaS;
    const medidaCaM = form.medidaCaM;
    const medidaCaL = form.medidaCaL;
    const medidaCaXL = form.medidaCaXL;
    const medidaCaXXL = form.medidaCaXXL;
    const medidaMBS = form.medidaMBS;
    const medidaMBM = form.medidaMBM;
    const medidaMBL = form.medidaMBL;
    const medidaMBXL = form.medidaMBXL;
    const medidaMBXXL = form.medidaMBXXL;
    const medidaMLS = form.medidaMLS;
    const medidaMLM = form.medidaMLM;
    const medidaMLL = form.medidaMLL;
    const medidaMLXL = form.medidaMLXL;
    const medidaMLXXL = form.medidaMLXXL;
    const medidaEAS = form.medidaEAS;
    const medidaEAM = form.medidaEAM;
    const medidaEAL = form.medidaEAL;
    const medidaEAXL = form.medidaEAXL;
    const medidaEAXXL = form.medidaEAXXL;
    const comentario = form.comentario;

    if(!isCantidadTalla(cantidadS.value)){
        setTallaError(cantidadS);
        validacion = false;
    } else {
        setTallaSuccess(cantidadS);
    }

    if(!isCantidadTalla(cantidadM.value)){
        setTallaError(cantidadM);
        validacion = false;
    } else {
        setTallaSuccess(cantidadM);
    }

    if(!isCantidadTalla(cantidadL.value)){
        setTallaError(cantidadL);
        validacion = false;
    } else {
        setTallaSuccess(cantidadL);
    }

    if(!isCantidadTalla(cantidadXL.value)){
        setTallaError(cantidadXL);
        validacion = false;
    } else {
        setTallaSuccess(cantidadXL);
    }

    if(!isCantidadTalla(cantidadXXL.value)){
        setTallaError(cantidadXXL);
        validacion = false;
    } else {
        setTallaSuccess(cantidadXXL);
    }

    if(composicion.value === '' || isEmpty(composicion.value)){
        setErrorFor(composicion, 'Ingrese una composicion');
        validacion = false;
    } else {
        setSuccessFor(composicion);
    }

    if(color.value === '' || isEmpty(color.value)){
        setErrorFor(color, 'Ingrese un color');
        validacion = false;
    } else {
        setSuccessFor(color);
    }

    if(disenio.value === '' || isEmpty(disenio.value)){
        setErrorFor(disenio ,'Ingrese un disenio');
        validacion = false;
    } else {
        setSuccessFor(disenio);
    }

    if(bordado.value === '' || isEmpty(bordado.value)){
        setErrorFor(bordado ,'Ingrese un bordado' );
        validacion = false;
    } else {
        setSuccessFor(bordado);
    }

    if(estampado.value === '' || isEmpty(estampado.value)){
        setErrorFor(estampado, 'Ingrese un estampado');
        validacion = false;
    } else {
        setSuccessFor(estampado);
    }

    if(tipoCuello.value === '' || isEmpty(tipoCuello.value)){
        setErrorFor(tipoCuello, 'Ingrese un tipo de cuello');
        validacion = false;
    } else {
        setSuccessFor(tipoCuello);
    }

    if(!isCheckSeleccionado(etiquetas)){
        setErrorFor(form.etiquetaValidacion, '');
        validacion = false;
    } else {
        setSuccessFor(form.etiquetaValidacion);
    }

    if(!isMedida(medidaPS.value)){
        setErrorFor(medidaPS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaPS);
    }

    if(!isMedida(medidaPM.value)){
        setErrorFor(medidaPM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaPM);
    }

    if(!isMedida(medidaPL.value)){
        setErrorFor(medidaPL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaPL);
    }

    if(!isMedida(medidaPXL.value)){
        setErrorFor(medidaPXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaPXL);
    }

    if(!isMedida(medidaPXXL.value)){
        setErrorFor(medidaPXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaPXXL);
    }

    if(!isMedida(medidaCiS.value)){
        setErrorFor(medidaCiS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCiS);
    }

    if(!isMedida(medidaCiM.value)){
        setErrorFor(medidaCiM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCiM);
    }

    if(!isMedida(medidaCiL.value)){
        setErrorFor(medidaCiL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCiL);
    }

    if(!isMedida(medidaCiXL.value)){
        setErrorFor(medidaCiXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCiXL);
    }

    if(!isMedida(medidaCiXXL.value)){
        setErrorFor(medidaCiXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCiXXL);
    }

    if(!isMedida(medidaCaS.value)){
        setErrorFor(medidaCaS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCaS);
    }

    if(!isMedida(medidaCaM.value)){
        setErrorFor(medidaCaM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCaM);
    }

    if(!isMedida(medidaCaL.value)){
        setErrorFor(medidaCaL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCaL);
    }

    if(!isMedida(medidaCaXL.value)){
        setErrorFor(medidaCaXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCaXL);
    }

    if(!isMedida(medidaCaXXL.value)){
        setErrorFor(medidaCaXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaCaXXL);
    }

    if(!isMedida(medidaMBS.value)){
        setErrorFor(medidaMBS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMBS);
    }

    if(!isMedida(medidaMBM.value)){
        setErrorFor(medidaMBM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMBM);
    }

    if(!isMedida(medidaMBL.value)){
        setErrorFor(medidaMBL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMBL);
    }

    if(!isMedida(medidaMBXL.value)){
        setErrorFor(medidaMBXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMBXL);
    }

    if(!isMedida(medidaMBXXL.value)){
        setErrorFor(medidaMBXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMBXXL);
    }

    if(!isMedida(medidaMLS.value)){
        setErrorFor(medidaMLS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMLS);
    }

    if(!isMedida(medidaMLM.value)){
        setErrorFor(medidaMLM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMLM);
    }

    if(!isMedida(medidaMLL.value)){
        setErrorFor(medidaMLL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMLL);
    }

    if(!isMedida(medidaMLXL.value)){
        setErrorFor(medidaMLXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMLXL);
    }

    if(!isMedida(medidaMLXXL.value)){
        setErrorFor(medidaMLXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaMLXXL);
    }

    if(!isMedida(medidaEAS.value)){
        setErrorFor(medidaEAS, '');
        validacion = false;
    } else {
        setSuccessFor(medidaEAS);
    }

    if(!isMedida(medidaEAM.value)){
        setErrorFor(medidaEAM, '');
        validacion = false;
    } else {
        setSuccessFor(medidaEAM);
    }

    if(!isMedida(medidaEAL.value)){
        setErrorFor(medidaEAL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaEAL);
    }

    if(!isMedida(medidaEAXL.value)){
        setErrorFor(medidaEAXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaEAXL);
    }

    if(!isMedida(medidaEAXXL.value)){
        setErrorFor(medidaEAXXL, '');
        validacion = false;
    } else {
        setSuccessFor(medidaEAXXL);
    }

    if(comentario.value === '' || isEmpty(comentario.value)){
        setErrorFor(comentario, '');
        validacion = false;
    } else {
        setSuccessFor(comentario);
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

function setTallaError(input) {
    const talla = input.parentElement
    talla.className = 'talla error'
}

function setTallaSuccess(input) {
    const talla = input.parentElement
    talla.className = 'talla success'
}

function isCantidadTalla(cantidadTalla){
    return /^\d{1,8}$/.test(cantidadTalla);
}

function isEmpty(texto){
    return /^\s+$/.test(texto);
}

function isCheckSeleccionado(etiquetas){
    for(var i = 0; i < etiquetas.length ; i++){
        if(etiquetas[i].checked){
            return true;
        }
    }
    return false;
}

function isMedida(medida){
    return /^\s*\d+\s*(\s+(\d\/\d){1})?$/.test(medida);
}
