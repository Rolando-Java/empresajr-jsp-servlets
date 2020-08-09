<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />

<c:forEach var="pedido" items="${pedidos}" varStatus="status">
    <form name="form1" action="${pageContext.request.contextPath}/ProduccionServlet?accion=ingresaravancediario&idPedido=${pedido.idPedido}" method="POST" onsubmit="return validarAvanceDiario(this)">
        <tr style="vertical-align: middle;">
            <th scope="row">${status.count}</th>
            <td>
                <button type="button" class="btn btn-success btn-sm m-0" data-toggle="modal"
                        data-target="#modalPedido${status.count}">
                    Ver <i class="fa fa fa-sort mr-0"></i>
                </button>
                <!-- Modal -->
                <div class="modal fade" id="modalPedido${status.count}" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title card-body" id="exampleModalLongTitle">
                                    Detalle del pedido
                                </h5>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <table class="table"
                                       style="background-color: #ffff; text-align: center;">
                                    <thead class="black white-text">
                                        <tr>
                                            <th scope="col">TIPO PRENDA</th>
                                            <th style="padding: 0px;" scope="col">CANTIDAD DE PRENDAS
                                            </th>
                                            <th scope="col">SUBTOTAL</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="detalle" items="${pedido.detallePedidos}" >
                                            <tr>
                                                <td>${detalle.prenda.tipoPrenda}</td>
                                                <td>${detalle.cantidadPrendas}</td>
                                                <td><fmt:formatNumber value="${detalle.subTotal}" type="currency" /></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            <td><input type="text" value="${pedido.cantidadPrendasTotal}" name="cantidadTotal" hidden>${pedido.cantidadPrendasTotal}</td>
            <td>${pedido.fecha}</td>
            <td><input type="text" value="${pedido.producciones.get(pedido.producciones.size()-1).cantidadAvance}" name="subTotal" hidden>${pedido.producciones.get(pedido.producciones.size()-1).cantidadAvance}</td>
            <td><input type="text" name="cantidadAvance"></td>
            <td><button class="btn btn-red rounded btn-sm" type="submit"><i class="fas fa-save"></i></button></td>
            <td><a href="${pageContext.request.contextPath}/ProduccionServlet?accion=concluirproduccion&idPedido=${pedido.idPedido}" class="btn btn-green rounded btn-sm"><i class="far fa-check-square"></i></a></td>
        </tr>
    </form>
</c:forEach>