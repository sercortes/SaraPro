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
                        <h4 class="card-title">Evaluar Productos Virtuales</h4>
                        <p class="card-category"></p>
                        <input type="hidden" value="${idUser}" id="webpagelink">
                    </div>
                    <div class="card-body table-responsive">

                        <table id="example" class="table table-hover table-borderless">
                            <thead class="gris text-center">
                                <tr class="">
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Autores</th>
                                    <th scope="col">Palabras clave</th>
                                    <th scope="col">Fecha Envío</th>
                                    <th scope="col">Opciones</th>
                                    <th scope="col">Evaluar</th>
                                </tr>
                            </thead>
                            <tfoot class="gris text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Autores</th>
                                    <th>Palabras clave</th>
                                    <th>Fecha Envío</th>
                                    <th>Opciones</th>
                                    <th>Evaluar</th>
                                </tr>
                            </tfoot>
                        </table>

                    </div>
                    
                </div>
            </div>
                    
                    <div id="cargas" class="loader loader-bouncing"></div>  

        </div>
    </div>
</div>
<%@include file="/pages/instructorTP/modalCalificar.jspf"%>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.dataTables.min.js"></script>
<script src="./assets/js/proyect/instructorTP/getProductos.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructorTP/evaluar.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructorTP/sendEvaluation.js" charset="utf-8"></script>


