<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />
<div class="pedidos-container">
    <div class="card" style="width: 20rem;">
        <div class="card-body">
            <h5 class="font-weight-bold mb-3">Detalle del pedido</h5>
            <p class="mb-0">Estos son los detalles de su pedido</p>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Fecha: ${detallePedidoGeneral.fecha}</li>
            <li class="list-group-item">Hora: ${detallePedidoGeneral.hora}</li>
            <li class="list-group-item">Prendas solicitadas: ${detallePedidoGeneral.cantidadPrendasTotal}</li>
            <li class="list-group-item">
                Total a pagar: <fmt:formatNumber value="${detallePedidoGeneral.total}" type="currency" />
            </li>
        </ul>
    </div>
</div>