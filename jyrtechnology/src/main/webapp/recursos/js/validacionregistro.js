function validarRegistro(form){
    
    let validacion = true;
    const nombre = form.nombre;
    const apellidoPaterno = form.apellidoPaterno;
    const apellidoMaterno = form.apellidoMaterno;
    const contrasenia = form.contrasenia;
    const cargo = form.cargo;
    const correo = form.correo;
    const direccion = form.direccion;
    const empresa = form.empresa;
    const ruc = form.ruc;
    const dni = form.dni;
    const telefono = form.telefono;
    const celular = form.celular;

    if(nombre.value === '' || isEmpty(nombre.value)){
        setErrorFor(nombre, 'Ingrese un nombre');
        validacion = false;
    } else if (!isNombre(nombre.value)){
        setErrorFor(nombre, 'Ingrese un nombre valido');
        validacion = false;
    } else {
        setSuccessFor(nombre);
    }

    if(apellidoPaterno.value === '' || isEmpty(apellidoPaterno.value)){
        setErrorFor(apellidoPaterno, 'Ingrese un apellido');
        validacion = false;
    } else if (!isApellidoPaterno(apellidoPaterno.value)){
        setErrorFor(apellidoPaterno, 'Ingrese un apellido valido');
        validacion = false;
    } else {
        setSuccessFor(apellidoPaterno);
    }

    if(apellidoMaterno.value === '' || isEmpty(apellidoMaterno.value)){
        setErrorFor(apellidoMaterno, 'Ingrese un apellido');
        validacion = false;
    } else if (!isApellidoMaterno(apellidoMaterno.value)){
        setErrorFor(apellidoMaterno, 'Ingrese un apellido valido');
        validacion = false;
    } else {
        setSuccessFor(apellidoMaterno);
    }

    if(contrasenia.value === '' || isEmpty(contrasenia.value)){
        setErrorFor(contrasenia, 'Ingrese una contrasenia');
        validacion = false;
    }else if (!isContrasenia(contrasenia.value)){
        setErrorFor(contrasenia,'No incluir espacios en la contrasenia');
        validacion = false;
    } else {
        setSuccessFor(contrasenia);
    }

    if(cargo.value === '' || isEmpty(cargo.value)){
        setErrorFor(cargo, 'Ingrese un cargo');
        validacion = false;
    } else {
        setSuccessFor(cargo);
    }

    if(correo.value === '' || isEmpty(correo.value)){
        setErrorFor(correo, 'Ingrese un correo');
        validacion = false;
    } else if (!isEmail(correo.value)){
        setErrorFor(correo, 'Ingrese una cuenta gmail');
        validacion = false;
    } else {
        setSuccessFor(correo);
    }
    
    if(direccion.value === '' || isEmpty(direccion.value)){
        setErrorFor(direccion, 'Ingrese una direccion');
        validacion = false;
    } else {
        setSuccessFor(direccion);
    }

    if(empresa.value === '' || isEmpty(empresa.value)){
        setErrorFor(empresa, 'Ingrese una empresa');
        validacion = false;
    } else {
        setSuccessFor(empresa);
    }

    if(ruc.value === '' || isEmpty(ruc.value)){
        setErrorFor(ruc, 'Ingrese un ruc');
        validacion = false;
    } else if(!isRuc(ruc.value)){
        setErrorFor(ruc, 'Ingrese un ruc valido');
        validacion = false;
    } else {
        setSuccessFor(ruc);
    }

    if(dni.value === '' || isEmpty(dni.value)){
        setErrorFor(dni, 'Ingrese un dni');
        validacion = false;
    } else if(!isDni(dni.value)){
        setErrorFor(dni, 'Ingrese un dni valido');
        validacion = false;
    } else {
        setSuccessFor(dni);
    }

    if(telefono.value === '' || isEmpty(telefono.value)){
        setErrorFor(telefono, 'Ingrese un telefono');
        validacion = false;
    } else if(!isTelefono(telefono.value)){
        setErrorFor(telefono, 'Ingrese un telefono valido');
        validacion = false;
    } else {
        setSuccessFor(telefono);
    }

    if(celular.value === '' || isEmpty(celular.value)){
        setErrorFor(celular, 'Ingrese un celular');
        validacion = false;
    } else if(!isCelular(celular.value)){
        setErrorFor(celular, 'Ingrese un celular valido');
        validacion = false;
    } else {
        setSuccessFor(celular);
    }

    return validacion;
}

function setErrorFor(input, message){
    const campo = input.parentElement
    const small = campo.querySelector('small')
    campo.className = 'campo error'
    small.innerText = message
}

function setSuccessFor(input){
    const formControl = input.parentElement
    formControl.className = 'campo success'
}

function isNombre(nombre){
    return /^([a-z]|[A-Z]){1}[a-z]+(\ ([a-z]|[A-Z]){1}[a-z]+){0,4}$/.test(nombre);
}

function isApellidoPaterno(apellidoPaterno){
    return /^([a-z]|[A-Z]){1}[a-z]+$/.test(apellidoPaterno);
}

function isApellidoMaterno(apellidoMaterno){
    return /^([a-z]|[A-Z]){1}[a-z]+$/.test(apellidoMaterno);
}

function isContrasenia(contrasenia){
    return /^\S+$/.test(contrasenia);
}

function isEmail(email){
    return /^\w+(\.(\w)+)*\@(gmail.com)$/.test(email);
}

function isRuc(ruc){
    return /^\d{11}$/.test(ruc);
}

function isDni(dni){
    return /^\d{8}$/.test(dni);
}

function isTelefono(telefono){
    return /^\d{7}$/.test(telefono);
}

function isCelular(celular){
    return /^\d{9}$/.test(celular);
}

function isEmpty(texto){
    return /^\s+$/.test(texto);
}