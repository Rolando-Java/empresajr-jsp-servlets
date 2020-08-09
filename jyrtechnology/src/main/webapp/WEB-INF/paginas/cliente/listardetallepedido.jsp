<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />
<table class="table table-bordered">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">TIPO DE PRENDA</th>
            <th scope="col">P/U</th>
            <th scope="col">CANTIDAD DE PRENDAS</th>
            <th scope="col">SUBTOTAL</th>
            <th scope="col">ELIMINAR</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="detalle" items="${detallePedido}" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${detalle.prenda.tipoPrenda}</td>
                <td><fmt:formatNumber value="${detalle.prenda.precioUnitario}" type="currency" /></td>
                <td>${detalle.cantidadPrendas}</td>
                <td><fmt:formatNumber value="${detalle.subTotal}" type="currency" /></td>
                <td><a href="${pageContext.request.contextPath}/PedidoServlet?accion=eliminarDetallePedido&idDetalle=${status.index}" style="color: white;"><i class="far fa-trash-alt"></i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>