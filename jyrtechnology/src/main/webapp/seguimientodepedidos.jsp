<%@page errorPage="WEB-INF/paginas/comunes/error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguimiento | J&R</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
        <script src="recursos/js/script.js"></script>
        <link rel="stylesheet" href="recursos/css/seguimiento.css">
        <link rel="icon" href="recursos/images/polo.ico">
    </head>

    <body>
        <jsp:include page="WEB-INF/paginas/cliente/menucliente.jsp" />
        <!-- seguimiento del pedido -->
        <jsp:include page="WEB-INF/paginas/cliente/verseguimiento.jsp" />
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
        <!-- Pinta las lineas de verde -->
        <script>
            const bullets = [...document.querySelectorAll('.bullet')];
            var estado = "${pedido.producciones.get(pedido.producciones.size()-1).estado}";
            if (estado === 'produccionterminadaalmacen') {
                bullets[0].classList.add('completed');
            } else if (estado === "almacen") {
                bullets[0].classList.add('completed');
                bullets[1].classList.add('completed');
            } else if (estado === "entregado") {
                bullets[0].classList.add('completed');
                bullets[1].classList.add('completed');
                bullets[2].classList.add('completed2');
            }
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
