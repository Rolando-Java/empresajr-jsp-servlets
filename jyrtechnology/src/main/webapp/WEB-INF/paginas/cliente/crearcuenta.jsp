<div class="container-fluid mb-5">
    <div class="row justify-content-center mt-5">
        <div class="col-4">
            <!-- Default form register -->
            <form name="form1" class="text-center border border-light p-5 formulario" id="formulario" action="${pageContext.request.contextPath}/SesionServlet?accion=registrar" method="POST" onsubmit="return validarRegistro(this)">

                <p class="h4 mb-4">REGISTRO</p>
                <div class="campo">
                    <input type="text" id="nombre" class="form-control mb-4"
                           placeholder="Nombre" name="nombre">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="form-row mb-4">
                    <div class="col">
                        <div class="campo">
                            <input type="text" id="primerapellido" class="form-control"
                                   placeholder="Primer apellido" name="apellidoPaterno">
                            <i class="fas fa-check-circle"></i>
                            <i class="fas fa-exclamation-circle"></i>
                            <small>Error message</small>
                        </div>
                    </div>
                    <div class="col">
                        <div class="campo">
                            <input type="text" id="segundoapellido" class="form-control"
                                   placeholder="Segundo apellido" name="apellidoMaterno">
                            <i class="fas fa-check-circle"></i>
                            <i class="fas fa-exclamation-circle"></i>
                            <small>Error message</small>
                        </div>
                    </div>
                </div>
                <div class="campo">
                    <input type="password" id="contraseña" class="form-control mb-4"
                           placeholder="Contraseña" name="contrasenia">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="cargo" class="form-control mb-4"
                           placeholder="Cargo" name="cargo">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="correo" class="form-control mb-4"
                           placeholder="Correo" name="correo">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="direccion" class="form-control mb-4"
                           placeholder="Dirección" name="direccion">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="empresa" class="form-control mb-4"
                           placeholder="Empresa" name="empresa">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="ruc" class="form-control mb-4"
                           placeholder="RUC" name="ruc">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="dni" class="form-control mb-4"
                           placeholder="DNI" name="dni">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="telefono" class="form-control mb-4"
                           placeholder="Teléfono" name="telefono">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="campo">
                    <input type="text" id="celular" class="form-control mb-4"
                           placeholder="Celular" name="celular">
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <div class="btnregistro">
                    <button class="btn btn-dark btn-block my-4" type="submit">REGISTRAR</button>
                </div>
                <div class="row pl-3">
                    <div class="volver">
                        <a href="${pageContext.request.contextPath}/SesionServlet">Regresar a Iniciar Sesión</a>
                    </div>
                </div>
                <div class="row">
                    <div class="container">
                        <img src="recursos/images/jr.png" alt="">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
