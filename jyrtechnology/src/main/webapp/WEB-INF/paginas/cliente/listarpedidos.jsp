<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />
<div class="col-md-10">
    <div class="row">
        <c:forEach var="pedido" items="${pedidos}" varStatus="status">
            <div class="col-md-6">
                <div class="pedidos-container">
                    <div class="detalles plan-light">
                        <ul class="list-group top-margin-xs">
                            <li class="list-group-item">
                                <h3>PEDIDO ${status.count}</h3>
                            </li>
                            <li class="list-group-item">
                                <h1><fmt:formatNumber value="${pedido.total}" type="currency" /></h1>
                            </li>
                            <li class="list-group-item">
                                <div class="md-v-line"></div><i
                                    class="fas fa-sort-numeric-down mr-2"></i>Cantidad de prendas: ${pedido.cantidadPrendasTotal}
                            </li>
                            <li class="list-group-item">
                                <div class="md-v-line"></div><i class="far fa-calendar-alt mr-2"></i>Fecha: ${pedido.fecha}
                            </li>
                            <li class="list-group-item">
                                <div class="md-v-line"></div><i class="fas fa-clock mr-2"></i>Hora: ${pedido.hora}
                            </li>
                            <li class="list-group-item">
                                <div class="md-v-line"></div><i class="fas fa-spinner mr-2"></i>Estado: ${pedido.estado}
                            </li>
                        </ul>
                        <div>
                            <c:if test="${pedido.fichaTecnica.idFichaTecnica == 0 && pedido.estado.equalsIgnoreCase('aceptado')}">
                                <a class="btn btn-red btn-block my-0" href="${pageContext.request.contextPath}/PedidoServlet?accion=ingresarFichaTecnica&idPedido=${pedido.idPedido}">Agregar ficha tecnica</a>
                            </c:if>
                            <c:if test="${pedido.fichaTecnica.idFichaTecnica > 0}">
                                <a class="btn btn-green btn-block my-0" href="${pageContext.request.contextPath}/PedidoServlet?accion=verSeguimiento&idPedido=${pedido.idPedido}">Ver seguimiento</a>
                            </c:if>
                        </div> 
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>