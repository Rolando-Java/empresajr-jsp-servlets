<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-PE" />

<c:forEach var="pedido" items="${pedidos}" varStatus="status">
    <tr style="vertical-align: middle;">
        <th scope="row">${status.count}</th>
        <td>
            <button type="button" class="btn btn-dark btn-sm m-0" data-toggle="modal"
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
        <td>${pedido.cantidadPrendasTotal}</td>
        <td>
            <button type="button" class="btn btn-dark btn-sm m-0" data-toggle="modal"
                    data-target="#modalDetalle${status.count}">
                Ver <i class="fa fa fa-sort mr-0"></i>
            </button>
            <!-- Modal -->
            <div class="modal fade" id="modalDetalle${status.count}" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content" style="width: inherit;">
                        <div class="modal-header">
                            <h5 class="modal-title card-body" id="exampleModalLongTitle"
                                style="text-align: center;">
                                Detalle de la ficha tecnica
                            </h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" style="text-align: center;">
                            <table class="table" style="background-color: #ffff; width: 100%;">
                                <thead class="black white-text">
                                    <tr>
                                        <th scope="col">TALLA</th>
                                        <th scope="col">S</th>
                                        <th scope="col">M</th>
                                        <th scope="col">L</th>
                                        <th scope="col">XL</th>
                                        <th scope="col">XXL</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>CANTIDAD</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <td>${talla.cantidad}</td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="table" style="background-color: #ffff; width: 100%;">
                                <thead class="black white-text">
                                    <tr>
                                        <th scope="col">COMPOSICION</th>
                                        <th scope="col">COLOR</th>
                                        <th scope="col">DISEÑO</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${pedido.fichaTecnica.composicion}</td>
                                        <td>${pedido.fichaTecnica.color}</td>
                                        <td>${pedido.fichaTecnica.disenio}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="table" style="background-color: #ffff; width: 100%;">
                                <thead class="black white-text">
                                    <tr>
                                        <th scope="col">BORDADO</th>
                                        <th scope="col">ESTAMPADO</th>
                                        <th scope="col">CUELLO</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${pedido.fichaTecnica.bordado}</td>
                                        <td>${pedido.fichaTecnica.estampado}</td>
                                        <td>${pedido.fichaTecnica.tipoCuello}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="table" style="background-color: #ffff; width: 100%;">
                                <thead class="black white-text">
                                    <tr>
                                        <th scope="col">ETIQUETA</th>
                                        <th scope="col" colspan="3">Características</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>ESCOGIDO</td>
                                        <c:forEach var="fichaTecnicaEtiqueta" items="${pedido.fichaTecnica.fichaTecnicaEtiquetas}" >
                                            <td>${fichaTecnicaEtiqueta.etiqueta.etiqueta}</td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="table" style="background-color: #ffff; width: 100%;">
                                <thead class="black white-text">
                                    <tr>
                                        <th scope="col">MEDIDAS</th>
                                        <th scope="col">Pecho</th>
                                        <th scope="col">Cintura</th>
                                        <th scope="col">Cadera</th>
                                        <th scope="col">Manga(Largo)</th>
                                        <th scope="col">Manga(Ancho)</th>
                                        <th scope="col">Espalda(Alto)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>S</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <c:if test="${talla.talla.equalsIgnoreCase('s')}" >
                                                <c:forEach var="tallaMedidaAtomica" items="${talla.tallaMedidaAtomicas}" >
                                                    <td>${tallaMedidaAtomica.medida}</td>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <td>M</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <c:if test="${talla.talla.equalsIgnoreCase('m')}" >
                                                <c:forEach var="tallaMedidaAtomica" items="${talla.tallaMedidaAtomicas}" >
                                                    <td>${tallaMedidaAtomica.medida}</td>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <td>L</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <c:if test="${talla.talla.equalsIgnoreCase('l')}" >
                                                <c:forEach var="tallaMedidaAtomica" items="${talla.tallaMedidaAtomicas}" >
                                                    <td>${tallaMedidaAtomica.medida}</td>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <td>XL</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <c:if test="${talla.talla.equalsIgnoreCase('xl')}" >
                                                <c:forEach var="tallaMedidaAtomica" items="${talla.tallaMedidaAtomicas}" >
                                                    <td>${tallaMedidaAtomica.medida}</td>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <td>XXL</td>
                                        <c:forEach var="talla" items="${pedido.fichaTecnica.tallas}" >
                                            <c:if test="${talla.talla.equalsIgnoreCase('xxl')}" >
                                                <c:forEach var="tallaMedidaAtomica" items="${talla.tallaMedidaAtomicas}" >
                                                    <td>${tallaMedidaAtomica.medida}</td>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="container">
                                <div class="form-group purple-border">
                                    <label for="exampleFormControlTextarea4"
                                           style="font-weight: bold;">Detalles y comentarios:</label>
                                    <textarea class="form-control" id="exampleFormControlTextarea4"
                                              rows="3" style="border:black 3px solid;">${pedido.fichaTecnica.comentario}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </td>
        <td>${pedido.producciones.get(pedido.producciones.size()-1).fecha}</td>
        <td><a href="${pageContext.request.contextPath}/AlmacenServlet?accion=ingresarPedidoTerminado&idPedido=${pedido.idPedido}" class="btn btn-red rounded btn-sm" style="color: white;"><i class="far fa-check-circle"></i></a></td>
    </tr>
</c:forEach>