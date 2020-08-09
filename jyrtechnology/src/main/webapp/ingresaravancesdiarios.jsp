<%@page errorPage="WEB-INF/paginas/comunes/error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avances diarios para perdidos en producción | J&R</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
        <script src="recursos/js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
        <link rel="stylesheet" href="recursos/css/avancesdiarios.css">
        <link rel="icon" href="recursos/images/polo.ico">
        <script type="text/javascript" src="recursos/js/validacionavancesdiarios.js"></script>
    </head>

    <body>
        <jsp:include page="WEB-INF/paginas/jefeproduccion/menujefeproduccion.jsp" />
        <div id="solicitudespedidos">
            <div class="container">
                <div class="content-center">
                    <h2>AVANCES DIARIOS PARA PEDIDOS EN PRODUCCIÓN<i class="far fa-calendar-alt"
                                                                     style="padding-left: 10px;"></i></h2>
                </div>
                <div class="row">
                    <table class="table" style="background-color: #ffff; text-align: center;">
                        <thead class="black white-text">
                            <tr>
                                <th scope="col">N°</th>
                                <th scope="col">DETALLE PEDIDO</th>
                                <th scope="col">CANTIDAD</th>
                                <th scope="col">FECHA ESTIMADA</th>
                                <th scope="col">AVANZADO</th>
                                <th scope="col">INGRESAR AVANCE</th>
                                <th scope="col">GUARDAR</th>
                                <th scope="col">CONCLUIR</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- establecer los avances diarios -->
                            <jsp:include page="WEB-INF/paginas/jefeproduccion/avancesdiarios.jsp" />
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Footer -->
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
