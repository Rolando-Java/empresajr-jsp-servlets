<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pedidos-container">
    <form name="form1" style="color: #ffff;" id="form" action="${pageContext.request.contextPath}/PedidoServlet?accion=registrarDetallePedido" method="POST" onsubmit="return validarRealizarPedido(this)">
        <!-- Default input -->
        <div class="form-group">
            <div class="campo">
                <label for="formGroupExampleInput">Tipo de prenda</label>
                <select class="form-control browser-default custom-select" name="tipoPrenda">
                    <option selected disabled>Seleccione su tipo de prenda</option>
                    <c:forEach var="prenda" items="${prendas}" varStatus="status">
                        <option value="${status.count}">${prenda.tipoPrenda}</option>
                    </c:forEach>
                </select>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small style="padding-top: 5.5%;">Error message</small>
            </div>
        </div>
        <!-- Default input -->
        <div class="campo">
            <label for="formGroupExampleInput2">Cantidad de prendas</label>
            <input type="text" class="form-control" id="cantidadprendas"
                   placeholder="Coloque la cantidad de prendas en unidades" name="cantidadPrendas">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <button class="btn btn-red btn-block my-4" type="submit">AÑADIR</button>
    </form>
</div>