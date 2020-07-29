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

    <body>
        <jsp:include page="WEB-INF/paginas/cliente/menucliente.jsp" />
        <div id="pedidos">
            <div class="container">
                <div class="content-center">
                    <h2>MIS PEDIDOS<i class="far fa-check-square" style="padding-left: 10px;"></i></h2>
                    <p>Los pedidos que usted ha realizado son los siguientes:</p>
                </div>
                <div class="row">
                    <jsp:include page="WEB-INF/paginas/cliente/listarpedidos.jsp" />
                    <jsp:include page="WEB-INF/paginas/cliente/filtrarpedidos.jsp" />
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer class="page-footer font-small stylish-color-dark lighten-3 pt-4">

            <!-- Footer Links -->
            <div class="container text-center text-md-left">

                <!-- Grid row -->
                <div class="row">
                    <hr class="clearfix w-100 d-md-none">

                    <!-- Grid column -->
                    <div class="col-md-2 col-lg-2 mx-auto my-md-4 my-0 mt-4 mb-1">

                        <!-- Links -->
                        <h5 class="font-weight-bold text-uppercase mb-4">NOSOTROS</h5>

                        <ul class="list-unstyled">
                            <li>
                                <p>
                                    <a href="#!">MIS PEDIDOS</a>
                                </p>
                            </li>
                            <li>
                                <p>
                                    <a href="#!">SEGUIMIENTO DEL PEDIDO</a>
                                </p>
                            </li>
                            <li>
                                <p>
                                    <a href="#!">REALIZAR PEDIDO</a>
                                </p>
                            </li>
                        </ul>

                    </div>
                    <!-- Grid column -->

                    <hr class="clearfix w-100 d-md-none">

                    <!-- Grid column -->
                    <div class="col-md-4 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                        <!-- Contact details -->
                        <h5 class="font-weight-bold text-uppercase mb-4">CONTACTO</h5>

                        <ul class="list-unstyled">
                            <li>
                                <p>
                                    <i class="fas fa-home mr-3"></i>Av.26 de mayo urb.los claveles de javier prado MZ A LT 23</p>
                            </li>
                            <li>
                                <p>
                                    <i class="fas fa-envelope mr-3"></i>janampaenma8@gmail.com</p>
                            </li>
                            <li>
                                <p>
                                    <i class="fas fa-phone mr-3"></i>951 438 518</p>
                            </li>
                        </ul>

                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

            </div>
            <!-- Footer Links -->

            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">© 2020 Copyright: J&R
            </div>
            <!-- Copyright -->

        </footer>
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
