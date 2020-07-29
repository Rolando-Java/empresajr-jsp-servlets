<%@page isErrorPage="true" %>
<jsp:scriptlet>
    if (exception != null) {
        request.setAttribute("excepcion", exception);
    }
</jsp:scriptlet>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="recursos/css/error.css">
    </head>

    <body>

        <div class="container contenedor">
            <header>
                <div class="row mt-3">
                    <div class="col-md-12">
                        <h1 class="display-3 titulo">ERROR</h1>
                    </div>
                </div>
            </header>
            <section>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <img src="recursos/images/pinguino-error.png" alt="">
                    </div>
                    <div class="col-md-4 offset-md-4">
                        <p class="text-danger parrafoerror">${excepcion.message}</p>
                        <a class="btn btn-danger btn-block" href="${pageContext.request.contextPath}/SesionServlet">Volver al inicio</a>
                    </div>
                </div>
            </section>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>

</html>
