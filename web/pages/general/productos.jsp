<%@include file="/pages/template/head.jspf"%>

<%@include file="/pages/template/validation.jspf"%>

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Consultar Productos Virtuales</h4>
                        <p class="card-category"></p>
                                 <input type="hidden" value="${idUser}" id="webpagelink">
                    </div>
                    <div class="card-body table-responsive">

                        <table id="example" class="table table-hover table-striped table-borderless">
                            <thead class="gris text-center">
                                <tr class="">
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Autores</th>
                                    <th scope="col">Palabras clave</th>
                                    <th scope="col">Fecha Env�o</th>
                                    <th scope="col">Detalles</th>
                                </tr>
                            </thead>
                            <tfoot class="gris text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Autores</th>
                                    <th>Palabras clave</th>
                                    <th>Fecha Env�o</th>
                                    <th>Detalles</th>
                                </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div>
            
                 <div id="cargas" class="loader loader-bouncing"></div>  
                    <input type="hidden" value="${idUser}" id="webpageuser">

        </div>
    </div>
</div>

<%@include file="/pages/general/modalDetails.jspf"%>
<%@include file="/pages/instructorTP/modalDownload.jspf"%>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.dataTables.min.js"></script>
<script src="./assets/js/proyect/general/consultar.js" charset="utf-8"></script>
<script src="./assets/js/proyect/general/DetailsPV.js" charset="utf-8"></script>
<%@include file="/pages/template/validationRols/notifications.jspf"%>


