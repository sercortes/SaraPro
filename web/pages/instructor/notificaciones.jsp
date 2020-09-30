<%@include file="/pages/template/head.jspf"%>

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<%@include file="/pages/template/validation.jspf"%>

<%@include file="/pages/template/validationRols/validationInstructor.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Notificaciones ${idRol}</h4>
                        <p class="card-category"></p>
                    </div>
                    <div class="card-body table-responsive">
                        
                         <table id="example" class="table table-hover table-striped">
                             <thead class="gris">
                                 <tr class="">
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Nombre</th>
                                </tr>
                            </thead>
                            <tfoot class="gris">
                                <tr>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Nombre</th>
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
<script src="./assets/js/proyect/instructor/notification.js" charset="utf-8"></script>


