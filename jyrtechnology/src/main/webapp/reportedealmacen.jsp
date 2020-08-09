<%@page errorPage="WEB-INF/paginas/comunes/error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Establecer fecha de producción | J&R</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Google Fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
        <script src="recursos/js/script.js"></script>
        <link rel="stylesheet" href="recursos/css/reportealmacen.css">
        <link rel="icon" href="recursos/images/polo.ico">
    </head>

    <body>
        <jsp:include page="WEB-INF/paginas/jefealmacen/menujefealmacen.jsp" />
        <div id="solicitudespedidos">
            <div class="container">
                <div class="content-center">
                    <h2>REPORTE DE ALMACÉN</h2>
                </div>
                <div class="row">
                    <table class="table" style="background-color: #ffff; text-align: center;">
                        <thead class="black white-text">
                            <tr>
                                <th scope="col">N°</th>
                                <th scope="col">DETALLE PEDIDO</th>
                                <th scope="col">CANTIDAD</th>
                                <th scope="col">FECHA INGRESO ALMACEN</th>
                                <th scope="col">ESTADO</th>
                                <th scope="col">CONFIRMAR ENTREGA</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- lista el reporte de almacen -->
                            <jsp:include page="WEB-INF/paginas/jefealmacen/reportealmacen.jsp" />
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
