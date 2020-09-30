<%@include file="/pages/template/head.jspf"%>

<link rel="stylesheet" type="text/css" href="./assets/css/multi-select.css">
<link rel="stylesheet" type="text/css" href="./assets/css/css-loader.css">

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
                        <h4 class="card-title">Subir Producto Virtual ${idRol}</h4>
                        <p class="card-category"></p>
                        <input id="usuario" type="hidden" value="${idUser}">
                    </div>
                    <div class="card-body">

                        <form id="formProduct" action="save?user=1">

                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Título del producto</label>
                                        <input type="text" class="form-control" name="nombreProducto" id="Titulo_Publicacion" placeholder="">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Descripción</label>
                                        <textarea class="form-control" name="descripcion" id="descripcion_oa" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Palabras Clave</label>
                                        <input type="text" class="form-control" name="palabrasClave" id="palabras_claves" placeholder="">
                                    </div>  

                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Instrucciones de Instalación</label>
                                        <textarea class="form-control" id="instrucciones" name="instrucciones" rows="3"></textarea>
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleFormControlInput1">Requisitos de Instalación</label>
                                        <input type="text" class="form-control" name="requisitosInstalacion" id="requisitos_instalacion" placeholder="">
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
                                        <select class="form-control" name="formatoArchivo" id="formato">
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

                            </div> <!-- cierre forrmulario -->

                            <h5 class="pt-3">Derechos de Autor</h5>

                            <div class="row">


                                <div class="form-group col-md-4">


                                    <div class="form-check">
                                        <input class="checkbox" id="r" name="derechosAutor" type="radio" value="r">
                                        <img class="img-fluid pl-2 pb-1" src="./assets/img/sara/form/1.png">
                                        <label class="form-check-label" for="defaultCheck1">
                                            Reconocimiento: El material creado por un autor puede ser distribuido, copiado y exhibido por terceros si se muestra en los créditos.
                                        </label>
                                    </div>


                                </div>

                                <div class="form-group col-md-4">

                                    <div class="form-check">
                                        <input class="checkbox" id="rs" name="derechosAutor" type="radio" value="rs">
                                         <img class="img-fluid pl-2 pb-1" src="./assets/img/sara/form/1.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/2.png">
                                        <label class="form-check-label" for="exampleRadios1">
                                            Reconocimiento - Sin obra derivada: El material creado por un autor puede ser distribuido, copiado y exhibido por terceros si se muestra en los créditos. No se pueden realizar obras derivadas.                                        </label>
                                    </div>
                                </div>

                                <div class="form-group col-md-4">

                                    <div class="form-check">
                                        <input class="checkbox" id="rn" name="derechosAutor" type="radio" value="rn">
                                         <img class="img-fluid pl-2 pb-1" src="./assets/img/sara/form/1.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/3.png">
                                        <label class="form-check-label" for="defaultCheck3">
                                            Reconocimiento - No comercial: El material creado por un autor puede ser distribuido, copiado y exhibido por terceros si se muestra en los créditos. No se puede obtener ningún beneficio comercial.
                                        </label>
                                    </div>

                                </div>

                            </div>

                            <div class="row">

                                <div class="form-group col-md-4">

                                    <div class="form-check">
                                        <input class="checkbox" id="rsn" name="derechosAutor" type="radio" value="rsn">
                                         <img class="img-fluid pl-2 pb-1" src="./assets/img/sara/form/1.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/2.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/3.png">
                                        <label class="form-check-label" for="defaultCheck5">
                                            Reconocimiento - Sin obra derivada - No comercial : El material creado por un autor puede ser distribuido, copiado y exhibido por terceros si se muestra en los créditos. No se puede obtener ningún beneficio comercial. No se pueden realizar obras derivadas.
                                        </label>
                                    </div>

                                </div>

                                <div class="form-group col-md-4">

                                    <div class="form-check">
                                        <input class="checkbox" id="rnc" name="derechosAutor" type="radio" value="rnc">
                                            <img class="img-fluid pl-2 pb-1" src="./assets/img/sara/form/1.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/4.png">
                                        <img class="img-fluid pb-1" src="./assets/img/sara/form/3.png">
                                        <label class="form-check-label" for="defaultCheck4">
                                            Reconocimiento - No comercial - Compartir igual : El material creado por un autor puede ser distribuido, copiado y exhibido por terceros si se muestra en los créditos. No se puede obtener ningún beneficio comercial y las obras derivadas tienen que estar bajo los mismos términos de licencia que el trabajo original.
                                        </label>
                                    </div>

                                </div>

                            </div> <!-- derechos de autor -->
                            
                            <div class="container">

                                <div class="row justify-content-md-center">

                                    <div class="col-md-auto">
                                        
                                         <h5 class="pt-3">Adjuntar archivo</h5>
                                    
                                        <div class="input-group">
                                            <div class="custom-file">
                                                <input type="file" class="" id="files" name="myfiles" aria-describedby="inputGroupFileAddon04" >
                                            </div>
                                        </div>
                         
                                    </div>

                                </div>

                            </div> <!-- cierree input type file -->
                            
                             <div class="container pt-3">

                                <div class="row justify-content-md-center">

                                    <div class="col-md-auto">
                                        
                                        <div class="input-group">
                                            <div class="custom-file float-right">
                                              
                                                <button id="send" type="button" class="btn btn-primary">Guardar</button>
                                                
                                            </div>
                                        </div>
                         
                                    </div>

                                </div>

                            </div> <!-- cierree input type file -->
                            
                              <div id="cargas" class="loader loader-bouncing"></div>  

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
<script src="./assets/js/proyect/instructor/upload/upload.js" charset="utf-8"></script>
<script src="./assets/js/proyect/instructor/upload/send.js" charset="utf-8"></script>


