<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="seguimiento">
    <div class="container">
        <div class="content-center">
            <h2>SEGUIMIENTO DE PEDIDO</h2>
        </div>
        <div id="stepProgressBar">
            <div class="step">
                <p class="step-text">PRODUCCIÓN</p>
                <img src="recursos/images/seguimiento1.png" style="width: 300px;"><br>
                <div class="bullet"><i class="fas fa-check"></i><i class="far fa-clock"></i></div>
                <p class="step-text">Última fecha de avance<br>
                    <c:choose>
                        <c:when test="${pedido.producciones.size() > 0}">
                            <span style="visibility: visible;">
                                ${pedido.producciones.get(0).fecha}&nbsp;&nbsp;${pedido.producciones.get(0).cantidadAvance}/${pedido.cantidadPrendasTotal}
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span style="visibility: hidden;">
                                00/00/00
                            </span>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="step">
                <p class="step-text">ALMACÉN</p>
                <img src="recursos/images/seguimiento2.png" style="width: 170px; padding-bottom: 30px; padding-top: 5px;"
                     alt=""><br>
                <div class="bullet"><i class="fas fa-check"></i><i class="far fa-clock"></i></div>
                <p class="step-text">Fecha de ingreso al almacén<br>
                    <c:choose>
                        <c:when test="${pedido.producciones.size() > 1}">
                            <span style="visibility: visible;">
                                ${pedido.producciones.get(1).fecha}
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span style="visibility: hidden;">
                                00/00/00
                            </span>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="step">
                <p class="step-text">ENTREGADO</p>
                <img src="recursos/images/seguimiento3.png" style="width: 300px; padding-top: 10px;" alt=""><br>
                <div class="bullet"><i class="fas fa-check"></i><i class="far fa-clock"></i></div>
                <p class="step-text">Fecha de entrega<br>
                    <c:choose>
                        <c:when test="${pedido.producciones.size() > 2}">
                            <span style="visibility: visible;">
                                ${pedido.producciones.get(2).fecha}
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span style="visibility: hidden;">
                                00/00/00
                            </span>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>

    </div>
</div>