<%@include file="/pages/template/head.jspf"%>

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
                        <h4 class="card-title">Nuevo usuario</h4>
                        <p class="card-category"></p>
                        <input type="hidden" value="${idUser}" id="webpagelink">
                    </div>
                    <div class="card-body table-responsive">

                        <form id="formProduct" action="save?user=1">

                            <div class="row">

                                <div class="col-md-6">

                                    <div class="form-group" id="temas">
                                        <label for="exampleFormControlInput1">Tipo documento</label>
                                        <select class="form-control select"  name="tipoIdenti" id="tipoIdenti">

                                        </select>
                                    </div>


                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Nombres</label>
                                        <input type="text" class="form-control" name="nombre" id="nombre" placeholder="">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Apellidos</label>
                                        <input type="text" class="form-control" name="apellido" id="apellido" placeholder="">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlInput1"># identificación</label>
                                        <input type="number" class="form-control" name="numeroIdentificacion" id="numeroIdentificacion" placeholder="">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Correo</label>
                                        <input type="email" class="form-control" name="email" id="email" placeholder="">
                                    </div>


                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Telefono</label>
                                        <input type="email" class="form-control" name="ipSena" id="ipSena" placeholder="">
                                    </div>


                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Cargo</label>
                                        <input type="email" class="form-control" name="cargo" id="cargo" placeholder="">
                                    </div>

                                </div>
                                <div class="col-md-6">

                                    <p>Roles del usuario</p>
                                    <div id="tipoUsu">
                                        <div class="form-check">
                                            <label>
                                                <input type="checkbox" id="Instructor" name="rol" value="1"> <span class="label-text">Instructor</span>
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <label>
                                                <input type="checkbox" id="tecnico" name="rol" value="2"> <span class="label-text">Equipo técnico</span>
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <label>
                                                <input type="checkbox" id="pedago" name="rol" value="3"> <span class="label-text">Equipo Pedagógico</span>
                                            </label>
                                        </div>

                                        <div class="form-check">
                                            <label>
                                                <input type="checkbox" id="coordina" name="rol" value="4"> <span class="label-text">Coordinador</span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Centro de formación</label>

                                        <select class="form-control select"  id="centroFormacion">

                                        </select>

                                    </div>

                                    <div class="form-group">

                                        <label  class="col-md-12"  for="area" id="redlabel">Red de Conocimiento:</label>
                                        <select class="form-control select" id="reddeconocimiento">

                                        </select>
                                    </div>

                                    <button id="botonModal" type="button" class="btn btn-primary float-right">Registro masivo</button>
                                    
                                </div>

                        </div> <!-- cierre forrmulario -->


                            <div class="container pt-3">

                                <div class="row justify-content-md-center">

                                    <div class="col-md-auto">

                                        <div class="input-group">
                                            <div class="custom-file float-right">

                                                <button id="boton1" type="button" class="btn btn-primary">Guardar</button>

                                            </div>
                                        </div>

                                    </div>

                                </div>

                            </div> <!-- cierree input type file -->

                        </form>

                    </div>

                </div>
            </div>

            <div id="cargas" class="loader loader-bouncing"></div>  

        </div>
    </div>
</div>
<%@include file="/pages/admin/modalAdd.jspf"%>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/proyect/admin/add.js" charset="utf-8"></script>
<script src="./assets/js/proyect/admin/carge.js" charset="utf-8"></script>

