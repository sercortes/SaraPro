<%@include file="/pages/template/head.jspf"%>

<link rel="stylesheet" type="text/css" href="./assets/css/css-loader.css">

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Corregir Producto Virtual</h4>
                        <p class="card-category"></p>
                    </div>
                    <div id="tabla" class="card-body table-responsive">

                        <table id="example" class="table table-hover">
                            <thead class="text-warning">
                                <tr class="">
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Versión</th>
                                    <th scope="col">Fecha Evaluación</th>
                                    <th scope="col">Evaluador</th>
                                    <th scope="col">Resultado</th>
                                    <th scope="col">Descargar</th>
                                    <th scope="col">Corregir</th>
                                </tr>
                            </thead>
                            <tfoot class="text-warning">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Versión</th>
                                    <th>Fecha Evaluación</th>
                                    <th>Evaluador</th>
                                    <th>Resultado</th>
                                    <th>Descargar</th>
                                    <th>Corregir</th>
                                </tr>
                            </tfoot>
                        </table>

                    </div>

                    <div id="eva" class="card-body table-responsive">

                        <h5 id="NomLista"></h5>
                        <h5 id="evaluador"></h5>
                        <h5 id="ObservacionGeneral"></h5>
                        <h5 id="FechaEvaluacion"></h5>

                        <div id="Respuestaitem">

                        </div>

                        <div class="row justify-content-md-center">

                            <div class="col-md-auto">

                                <h5 class="pt-3">Adjuntar archivo</h5>
                                <form id="files">
                                <div class="input-group">
                                    <div class="custom-file">
                                        <input type="file" class="" id="newFile" name="newFile" aria-describedby="inputGroupFileAddon04" >
                                    </div>
                                </div>
                                </form>

                            </div>

                        </div>

                        <button id="SendFile" type="button" class="btn btn-primary float-right">Guardar</button>

                        <div id="cargas" class="loader loader-bouncing"></div>  

                    </div>

                </div>
            </div>

        </div>
    </div>
</div>


<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.dataTables.min.js"></script>
<script src="./assets/js/proyect/instructor/corrregir/Corregir.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructor/corrregir/Corregir2.js" charset="utf-8"></script>


