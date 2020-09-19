<%@include file="/pages/template/head.jspf"%>

<link rel="stylesheet" type="text/css" href="./assets/css/multi-select.css">

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-warning">
                        <h4 class="card-title">Subir Producto Virtual ${idRol}</h4>
                        <p class="card-category"></p>
                        <input id="usuario" type="hidden" value="${idUser}">
                    </div>
                    <div class="card-body">

                        <form>

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Título del producto</label>
                                        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Descripción</label>
                                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Palabras Clave</label>
                                        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
                                    </div>  

                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Instrucciones de Instalación</label>
                                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Requisitos de Instalación</label>
                                        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
                                    </div>


                                
                                    
                                  
                                    
                                    <div class="form-group">
                                        <label for="exampleFormControlSelect1">Buscar por Categorías</label>
                                          <select class="form-control" id="SelectCategoria" > 
                                                </select>
                                    </div>
                                    
                                    <div class="form-group" id="temas">
                                        <label for="exampleFormControlInput1">Temas</label>
                                        <select required  id="MultiCategoriass" class="dd"  multiple='multiple' title="Busca un tema...">
                                        </select>
                                    </div>

                                </div>

                                <div class="col-md-6">
                                    
                                        <div class="form-group">
                                        <label for="exampleFormControlSelect1">Formato del Archivo</label>
                                        <select class="form-control" id="exampleFormControlSelect1">
                                            <option value="" selected="selected">Seleccionar...</option>
                                            <option value="4">Audios</option>
                                            <option value="2">Documento</option>
                                            <option value="3">Imagen</option>
                                            <option value="1">Vídeo</option>
                                        </select>
                                    </div>
                                    
                                    <hr style="visibility: hidden">
                                    
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Autores</label>
                                        <select required  id="SelectAutores" class="autoresMultiselect"  multiple='multiple' title="Busca un autor..">
                                            <option  disabled selected>${nomUser}</option>
                                        </select>
                                    </div>


                                </div>

                            </div> <!-- cierre row -->

                        </form>

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
<script src="./assets/js/proyect/instructor/upload.js" charset="utf-8"></script>


