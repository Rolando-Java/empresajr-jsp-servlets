<%@page errorPage="WEB-INF/paginas/comunes/error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis pedidos | J&R</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
        <script src="recursos/js/script.js"></script>
        <link rel="stylesheet" href="recursos/css/menu.css">
        <link rel="icon" href="recursos/images/polo.ico">
    </head>
    <jsp:include page="WEB-INF/paginas/comunes/mensajeAlert.jsp" />
    <body>
        <jsp:include page="WEB-INF/paginas/cliente/menucliente.jsp" />
        <div id="pedidos">
            <div class="container">
                <div class="content-center">
                    <h2>MIS PEDIDOS<i class="far fa-check-square" style="padding-left: 10px;"></i></h2>
                    <p>Los pedidos que usted ha realizado son los siguientes:</p>
                </div>
                <div class="row">
                    <!-- listar pedidos -->
                    <jsp:include page="WEB-INF/paginas/cliente/listarpedidos.jsp" />
                    <!-- filtrar pedidos -->
                    <jsp:include page="WEB-INF/paginas/cliente/filtrarpedidos.jsp" />
                </div>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="WEB-INF/paginas/cliente/piedepaginacliente.jsp" />
        <!-- Footer -->
        <script>
            const btn = document.querySelector('#menu-btn');
            const menu = document.querySelector('#sidemenu');
            btn.addEventListener('click', e => {
                menu.classList.toggle("menu-expanded");
                menu.classList.toggle("menu-collapsed");
                document.querySelector('body').classList.toggle('body-expanded');
            });
        </script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/js/mdb.min.js"></script>
    </body>

</html>
