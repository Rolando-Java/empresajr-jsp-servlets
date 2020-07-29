<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión | J&R</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
        <link rel="stylesheet" href="recursos/css/estilos.css">
        <link rel="icon" href="recursos/images/polo.ico">
    </head>
    <jsp:include page="WEB-INF/paginas/comunes/mensajeAlert.jsp" />
    <body>
        <!-- Default form login -->
        <div class="container-fluid">
            <div class="row justify-content-center pt-5 mt-5">
                <div class="col-4">
                    <form name="form1" class="text-center border border-light p-5 formulario" action="${pageContext.request.contextPath}/SesionServlet?accion=iniciar" method="POST"> 
                        
                        <p class="h4 mb-4">INICIAR SESIÓN</p>
                        
                        <!-- Email -->
                        <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" 
                               placeholder="Usuario" name="usuario">

                        <!-- Password -->
                        <input type="password" id="defaultLoginFormPassword" class="form-control mb-4"
                               placeholder="Contraseña" name="contrasenia">
                        <div class="btnregistro">
                            <button class="btn btn-dark btn-block my-4" type="submit">INGRESAR</button>
                        </div>
                        <div class="registro">
                            <a href="${pageContext.request.contextPath}/SesionServlet?accion=registrar">Si aún no tienes una cuenta, ¡Registrate aquí!</a>
                        </div>
                        <div class="container">
                            <img src="recursos/images/jr.png" alt="">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Default form login -->


        <!-- JQuery -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/js/mdb.min.js"></script>
    </body>

</html>
