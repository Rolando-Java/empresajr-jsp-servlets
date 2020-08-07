<div id="fichatecnica">
    <form name="form1" id="formulario" action="${pageContext.request.contextPath}/PedidoServlet?accion=ingresarFichaTecnica&idPedido=${idPedido}" method="POST" onsubmit="return validarFichaTecnica(this)">
        <div class="container">
            <div class="content-center">
                <h2>INGRESAR FICHA TÉCNICA<i class="fas fa-book-open" style="padding-left: 15px;"></i></h2>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="arribaizq" style="border: solid 1px; margin-bottom: 30px;">
                        <div class="container">
                            <table class="table table-borderless" style="color: #fff; text-align: center;">
                                <thead>
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
                                        <th scope="row">CANTIDAD</th>
                                        <td>
                                            <div class="talla">
                                                <input type="text" id="S" name="cantidadS">
                                                <i class="fas fa-check-circle"></i>
                                                <i class="fas fa-exclamation-circle"></i>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="talla">
                                                <input type="text" id="M" name="cantidadM">
                                                <i class="fas fa-check-circle"></i>
                                                <i class="fas fa-exclamation-circle"></i>
                                            </div>

                                        </td>
                                        <td>
                                            <div class="talla">
                                                <input type="text" id="L" name="cantidadL">
                                                <i class="fas fa-check-circle"></i>
                                                <i class="fas fa-exclamation-circle"></i>
                                            </div>

                                        </td>
                                        <td>
                                            <div class="talla">
                                                <input type="text" id="XL" name="cantidadXL">
                                                <i class="fas fa-check-circle"></i>
                                                <i class="fas fa-exclamation-circle"></i>
                                            </div>

                                        </td>
                                        <td>
                                            <div class="talla">
                                                <input type="text" id="XXL" name="cantidadXXL">
                                                <i class="fas fa-check-circle"></i>
                                                <i class="fas fa-exclamation-circle"></i>
                                            </div>

                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Composición de la tela</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="composicion" class="form-control mb-4" name="composicion">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Color</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="#" class="form-control mb-4" name="color">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Diseño</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="#" class="form-control mb-4" name="disenio">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Bordado</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="#" class="form-control mb-4" name="bordado">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Estampado</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="#" class="form-control mb-4" name="estampado">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Tipo de Cuello</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <input type="text" id="#" class="form-control mb-4" name="tipoCuello">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4" style="text-align: right;">
                                    <label for="">Etiqueta</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="campo">
                                        <!-- Default inline 1-->
                                        <div class="custom-control custom-checkbox custom-control-inline">
                                            <input type="checkbox" class="custom-control-input" id="defaultInline1" name="etiqueta" value="1">
                                            <label class="custom-control-label" for="defaultInline1">Talla</label>
                                        </div>

                                        <!-- Default inline 2-->
                                        <div class="custom-control custom-checkbox custom-control-inline">
                                            <input type="checkbox" class="custom-control-input" id="defaultInline2" name="etiqueta" value="2">
                                            <label class="custom-control-label" for="defaultInline2">Marca</label>
                                        </div>

                                        <!-- Default inline 3-->
                                        <div class="custom-control custom-checkbox custom-control-inline">
                                            <input type="checkbox" class="custom-control-input" id="defaultInline3" name="etiqueta" value="3">
                                            <label class="custom-control-label" for="defaultInline3">Composición</label>
                                        </div>
                                        <input class="custom-control-input" id="defaultInline3" name="etiquetaValidacion" hidden/>
                                        <i class="fas fa-check-circle pt-0"></i>
                                        <i class="fas fa-exclamation-circle pt-0"></i>
                                        <small>Error message</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <img src="recursos/images/tshirt4.png" alt="" style="width: 150%;">
                </div>
            </div>
        </div>

        <div id="tablamedidas">
            <div class="container">
                <div class="row">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">MEDIDAS ANATÓMICAS</th>
                                <th scope="col">S</th>
                                <th scope="col">M</th>
                                <th scope="col">L</th>
                                <th scope="col">XL</th>
                                <th scope="col">XXL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Pecho</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaPS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaPM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaPL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaPXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaPXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Cintura</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCiS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCiM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCiL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCiXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCiXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Cadera</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCaS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCaM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCaL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCaXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaCaXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Manga(Basta)</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMBS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMBM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMBL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMBXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMBXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Manga(Largo)</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMLS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMLM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMLL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMLXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaMLXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Espalda(Alto)</th>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaEAS">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaEAM">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaEAL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaEAXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                                <td>
                                    <div class="campo">
                                        <input type="text" style="width: 50px;" name="medidaEAXXL">
                                        <i class="fas fa-check-circle"></i>
                                        <i class="fas fa-exclamation-circle"></i>
                                        <small>Error message</small>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="form-group purple-border">
                <div class="campo">
                    <label for="exampleFormControlTextarea4">Detalles y comentarios:</label>
                    <textarea class="form-control" id="exampleFormControlTextarea4" rows="3" name="comentario"></textarea>
                    <i class="fas fa-check-circle"></i>
                    <i class="fas fa-exclamation-circle"></i>
                    <small>Error message</small>
                </div>
                <button class="btn btn-red btn-block my-4" type="submit">AÑADIR</button>
            </div>
        </div>
    </form>
</div>