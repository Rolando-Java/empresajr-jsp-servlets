<div class="col-md-2">
    <p style="color: #ffff; text-align: center; float: right;">Filtrar por estado</p>
    <div class="estados">
        <select class="browser-default custom-select"
                onchange="window.location = this.options[this.selectedIndex].value">
            <option selected disabled value=""></option>
            <option value="${pageContext.request.contextPath}/PedidoServlet?accion=listarpedidosByUsuario&estadoPedido=aceptado">Aceptado</option>
            <option value="${pageContext.request.contextPath}/PedidoServlet?accion=listarpedidosByUsuario&estadoPedido=rechazado">Rechazado</option>
            <option value="${pageContext.request.contextPath}/PedidoServlet?accion=listarpedidosByUsuario&estadoPedido=en espera">En espera</option>
        </select>
    </div>
</div>