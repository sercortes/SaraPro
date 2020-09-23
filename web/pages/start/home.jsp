
<%@include file="/pages/template/head.jspf"%>

<%@include file="/pages/template/validation.jspf"%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">¿Con cuál rol deseas ingresar?</h5>
            </div>
            <input type="hidden" value="${idUser}" id="idUser" />
            <div class="modal-body">



                <div class="row h-100">
                    <div class="col-md-12 my-auto">
                        <img src="./assets/img/sara/logofinal.png" class="rounded mx-auto d-block"> 
                    </div>
                   
                    <div class="p-2 col text-center" id="buttons">
                <button type="button" class="btn btn-info">Instructor</button>
                <button type="button" class="btn btn-primary">Instructor Técnico</button>
                <button type="button" class="btn btn-warning">Instructor Pedagógico</button>
                   </div>
                </div>
                
             
                
            </div>
            <div class="modal-footer" id="modalF">
              
                 <a href="#" id="fecha" class="d-none d-print-block">
                  <i class="material-icons">laptop</i> 
                </a>
                
            </div>

        </div>
    </div>
</div>
</div>


<%@include file="/pages/template/footer.jspf"%>

<script src="./assets/js/proyect/login/roles.js" charset="utf-8"></script>



