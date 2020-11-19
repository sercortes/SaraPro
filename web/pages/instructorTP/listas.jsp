<%@include file="/pages/template/head.jspf"%>

<link rel="stylesheet" type="text/css" href="./assets/css/multi-select.css">
<link rel="stylesheet" type="text/css" href="./assets/css/css-loader.css">

<%@include file="/pages/template/validation.jspf"%>

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<%@include file="/pages/template/validationRols/validationTP.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Crear lista de chequeo</h4>
                        <p class="card-category"></p>
                        <input type="hidden" value="${idUser}" id="webpagelink">
                    </div>
                    <div class="card-body table-responsive">

                        <form id="formListas" action="save?user=1">

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Nombre de la lista</label>
                                        <input type="text" class="form-control" name="nomLista" id="nombreLista" placeholder="">
                                    </div>



                                </div>
                                <div class="col-md-6">

                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Descripción</label>
                                        <textarea class="form-control" name="descripcion" id="descipcionLista" rows="3"></textarea>
                                    </div>



                                </div>

                                <div class="col-md-6">

                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Nuevo Item</label>
                                        <input type="text" class="form-control" name="nomLista" id="nombreItem" placeholder="">
                                    </div>

                                    <div>
                                        <button id="addItem" type="button" class="btn btn-primary float-right">Agregar</button>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <hr style="visibility: hidden">

                                    <div class="form-group col-md-12">
                                        <label for="exampleFormControlInput1">Items</label>
                                        <select required  id="SelectItems" class="autoresMultiselect"  multiple='multiple' title="Busca un autor..">
                                        </select>
                                    </div>

                                </div>

                            </div> <!-- cierre forrmulario -->

                            <div class="container pt-3">

                                <div class="row">

                                    <div class="col-md-12">

                                        <div class="custom-file float-right">

                                            <button id="send" type="button" class="btn btn-primary float-right">Guardar</button>

                                        </div>

                                    </div>

                                </div>

                            </div> <!-- cierree input type file -->

                            <div id="cargas" class="loader loader-bouncing"></div>  

                        </form>

                        <hr class="pt-2">

                        <div class="container"> <!-- container cargue masivo-->

                            <div class="row justify-content-md-center">

                                <div class="col-md-auto">

                                    <h5 class="pt-3">Adjuntar archivo</h5>
                                    
                                      <div class="row">
                            <div class="col-lg-12">
                                <p>Descargar el siguiente formato.
                                    <a class="btn btn-primary btn-sm" href="./assets/files/FormatoListasChequeo.xls">
                                        <i class="material-icons md-48">cloud_download</i> Descargar</a></p>
                                <ul>
                                    <li>
                                        Recuerde agregar nombre y descripción en el formato.
                                    </li>
                                    <li>
                                        Asegúrese de diligenciar de manera correcta, de lo contrario el sistema lo reportará.
                                    </li>
                                </ul>

                            </div>

                        </div>
                                    
                                    <form id="formMasivo" action="CargaMasiva" method="POST" enctype="multipart/form-data">

                                        <div class="input-group">
                                            <div class="custom-file">
                                                <input type="file" class="" id="files" name="files" aria-describedby="inputGroupFileAddon04" >
                                            </div>
                                        </div>

                                    </form>

                                </div>

                            </div>

                        </div> <!-- cierree input type file -->

                      

                        <div class="container pt-3">

                            <div class="row justify-content-md-center">

                                <div class="col-md-auto">

                                    <div class="input-group">
                                        <div class="custom-file float-right">

                                            <button id="botonCargeMas" type="button" class="btn btn-primary">Enviar</button>

                                        </div>
                                    </div>

                                </div>

                            </div>

                        </div>  <!-- cierre container cargue masivo--> 

                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.multi-select.js" charset="utf-8"></script>
<script src="./assets/js/plugins/jquery.quicksearch.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructorTP/listas/lista.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructorTP/listas/lista2.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructorTP/listas/carge.js" charset="utf-8"></script>

<%@include file="/pages/template/validationRols/notifications.jspf"%>


