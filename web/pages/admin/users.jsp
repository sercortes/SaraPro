<%@include file="/pages/template/head.jspf"%>

<link rel="stylesheet" type="text/css" href="./assets/css/multi-select.css">
<link rel="stylesheet" type="text/css" href="./assets/css/css-loader.css">

<%@include file="/pages/template/validation.jspf"%>

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<%@include file="/pages/template/validationRols/validationAdmin.jspf"%>


<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Usuarios</h4>
                        <p class="card-category"></p>
                    </div>
                    <div class="card-body table-responsive">

                        <table id="example" class="table table-hover table-striped table-borderless">
                            <thead class="gris text-center">
                                <tr class="">
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Autores</th>
                                    <th scope="col">Palabras clave</th>
                                    <th scope="col">Área</th>
                                    <th scope="col">Centro</th>
                                    <th scope="col">Detalles</th>
                                </tr>
                            </thead>
                            <tfoot class="gris text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Autores</th>
                                    <th>Palabras clave</th>
                                    <th>Área</th>
                                    <th>Centro</th>
                                    <th>Detalles</th>
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

<%@include file="/pages/admin/modalUsers.jspf"%>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.dataTables.min.js"></script>
<script src="./assets/js/plugins/jquery.multi-select.js" charset="utf-8"></script>
<script src="./assets/js/plugins/jquery.quicksearch.js" charset="utf-8"></script>

<script src="./assets/js/proyect/admin/users/users.js" charset="utf-8"></script>
<script src="./assets/js/proyect/admin/users/add.js" charset="utf-8"></script>

