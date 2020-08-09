<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />
<div class="row">
    <table class="table" style="background-color: #ffff; text-align: center;">
        <thead class="black white-text">
            <tr>
                <th scope="col">N°</th>
                <th scope="col">CLIENTE</th>
                <th scope="col">FECHA</th>
                <th scope="col">TOTAL DE PRENDAS</th>
                <th scope="col">TOTAL A PAGAR</th>
                <th scope="col">DETALLE</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pedido" items="${pedidos}" varStatus="status" >
                <tr>
                    <th scope="row">${status.count}</th>
                    <td>${pedido.usuario.apellidoPaterno} ${pedido.usuario.apellidoMaterno}, ${pedido.usuario.nombre}</td>
                    <td>${pedido.fecha}</td>
                    <td>${pedido.cantidadPrendasTotal}</td>
                    <td><fmt:formatNumber value="${pedido.total}" type="currency" /></td>
                    <td>
                        <button type="button" class="btn btn-success btn-sm m-0" data-toggle="modal"
                                data-target="#modalDetalle${status.count}">
                            Ver <i class="fa fa fa-sort mr-0"></i>
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="modalDetalle${status.count}" tabindex="-1" role="dialog"
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
                                                    <th scope="col">CANTIDAD DE PRENDAS</th>
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
                    <td>
                        <button type="button" class="btn btn-dark btn-sm m-0" data-toggle="modal"
                                data-target="#modalAceptado${status.count}">ACEPTAR</button>
                        <button type="button" class="btn btn-dark btn-sm m-0" data-toggle="modal"
                                data-target="#modalRechazado${status.count}">RECHAZAR</button>

                        <!-- Modal -->
                        <form name="form" action="${pageContext.request.contextPath}/SolicitudPedidoServlet?accion=solicitudPedido&idPedido=${pedido.idPedido}&estadoPedido=aceptado" method="POST" onsubmit="return validarSolicitudPedido(this)">
                            <div class="modal fade" id="modalAceptado${status.count}" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title card-body" id="exampleModalLongTitle">
                                                Detalle del correo
                                            </h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12" style="font-size: 1.2em;text-align: left;">
                                                    <label for="formGroupExampleInput2">Mensaje:</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <textarea class="form-control"
                                                              id="formGroupExampleInput2" name="mensaje"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-dark">Enviar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- Modal -->
                        <form name="form" action="${pageContext.request.contextPath}/SolicitudPedidoServlet?accion=solicitudPedido&idPedido=${pedido.idPedido}&estadoPedido=rechazado" method="POST" onsubmit="return validarSolicitudPedido(this)">
                            <div class="modal fade" id="modalRechazado${status.count}" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title card-body" id="exampleModalLongTitle">
                                                Detalle del correo
                                            </h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-12" style="font-size: 1.2em;text-align: left;">
                                                    <label for="formGroupExampleInput2">Mensaje:</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <textarea class="form-control"
                                                              id="formGroupExampleInput2" name="mensaje"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-dark">Enviar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>