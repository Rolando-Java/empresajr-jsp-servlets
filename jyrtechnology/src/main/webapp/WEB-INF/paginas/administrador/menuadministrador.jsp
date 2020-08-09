<div id="sidemenu" class="menu-collapsed">
    <div id="header">
        <div id="tittle"><span>Empresa textil J&R</span></div>
        <div id="menu-btn">
            <div class="btn-hamburger"></div>
            <div class="btn-hamburger"></div>
            <div class="btn-hamburger"></div>
        </div>
    </div>

    <div id="profile">
        <div id="photo">
            <h4>${usuario.usuario.toUpperCase().charAt(0)}</h4>
        </div>
        <div id="name"><span>${usuario.usuario}</span></div>
    </div>

    <div id="menu-items">
        <div class="item">
            <a href="${pageContext.request.contextPath}/SolicitudPedidoServlet?accion=listarPedidos">
                <div class="icon"><img src="recursos/images/solicitudes.png" alt=""></div>
                <div class="title"><span>Solicitudes de pedidos</span></div>
            </a>
        </div>
        <div class="item separator"></div>
        <div class="cerrar">
            <h6><a href="${pageContext.request.contextPath}/SesionServlet?accion=cerrar">Cerrar sesi�n</a></h6>
        </div>
    </div>
</div>