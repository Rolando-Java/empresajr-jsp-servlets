<div class="row mb-3">
    <div class="col-md-4 p-0" style="text-align: left;">
        <p style="color: #ffff;">Filtrar por estado</p>
        <div class="estados" style="float: none;">
            <select class="browser-default custom-select"
                    onchange="window.location = this.options[this.selectedIndex].value">
                <option selected disabled value=""></option>
                <option value="${pageContext.request.contextPath}/ProduccionServlet?accion=listarPedidos&estadoProduccion=produccioniniciada">Producción iniciada</option>
                <option value="${pageContext.request.contextPath}/ProduccionServlet?accion=listarPedidos&estadoProduccion=produccionpausa">Producción en pausa</option>
                <option value="${pageContext.request.contextPath}/ProduccionServlet?accion=listarPedidos&estadoProduccion=produccionavance">En producción</option>
                <option value="${pageContext.request.contextPath}/ProduccionServlet?accion=listarPedidos&estadoProduccion=produccionterminada">Producción terminada</option>
            </select>
        </div>
    </div>
</div>