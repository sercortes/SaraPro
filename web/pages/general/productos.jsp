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
                    </div>
                    <div class="card-body table-responsive">

                        <table id="example" class="table table-hover">
                            <thead class="text-warning">
                                <tr class="">
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Autores</th>
                                    <th scope="col">Descripción</th>
                                </tr>
                            </thead>
                            <tfoot class="text-warning">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Autores</th>
                                    <th>Descripción</th>
                                </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/plugins/jquery.dataTables.min.js"></script>
<script src="./assets/js/proyect/instructor/products/consultar.js" charset="utf-8"></script>


