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
                        <h4 class="card-title">Cambiar clave</h4>
                        <p class="card-category"></p>
                    </div>
                    <div class="card-body table-responsive">


                        <form id="formularioPass" action="save?user=1">

                            <div class="row">

                                <div class="col-md-6">
                                    <label for="exampleFormControlInput1">Nueva clave</label>
                                    <div class="input-group">
                                        <input id="passOne" type="password" class="form-control pwd" placeholder="*******">
                                        <span class="input-group-btn" style="padding: 0px">
                                            <button class="btn btn-default reveal" type="button"> <i class="material-icons">vpn_key</i></button>
                                        </span>   
                                    </div>

                                </div>

                                <div class="col-md-6">
                                    <label for="exampleFormControlInput1">Nueva clave</label>
                                    <div class="input-group">
                                        <input id="passTwo" type="password" class="form-control pwd" placeholder="*******">
                                        <span class="input-group-btn" style="padding: 0px">
                                            <button class="btn btn-default reveal" type="button"> <i class="material-icons">vpn_key</i></button>
                                        </span>   
                                    </div>


                                </div>

                            </div> <!-- cierre forrmulario -->

                            <button id="changePass" type="button" class="btn btn-primary float-right">Guardar</button>
                            
                        </form>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>


<script src="./assets/js/proyect/instructor/pass/pass.js" charset="utf-8"></script>
<%@include file="/pages/template/validationRols/notifications.jspf"%>


