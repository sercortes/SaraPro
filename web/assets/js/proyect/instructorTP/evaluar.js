var idVersion

$(document).on('click', '.btnEvaluar', function (e) {

    let nombre = this.value
    idVersion = this.id;
    
    console.log(idVersion)

    getListas();

    $('#modalC').modal({
        backdrop: 'static',
        keyboard: false
    })
    $('#modalTittle').text(nombre)

    $('#modalC').modal('show')

});

function getListas() {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getList",
        success: function (data) {

            console.log(data)
            
             $('#listas').dataTable({
                 "bDestroy": true,
                "processing": true,
                "aaData": data,
                "columns": [
                    {"data": "nombre"},
                    {"data": "descripcion"},
                    {"data": "creador"},
                     {
                        "mData": "idLista",
                        "mRender": function (data, type, row) {
                            return `<button type="button" id="${row.nombre}" class="btn btn-info calificar" value="${row.idLista}">Seleccionar</button>`;
                        }
                    },
                ],
                "language": {
                    "lengthMenu": "Mostrar _MENU_ x página",
                    "zeroRecords": "No encontrados",
                    "info": "página _PAGE_ de _PAGES_",
                    "infoEmpty": "No hay elementos",
                    "infoFiltered": "(total _MAX_ )"
                }
            })
            

        },
        error: function (data) {

            console.log(data)

        }

    })

}

$(document).on('click', '.calificar', function (e) {
    
    let idLista = this.value
    console.log(idLista)
    console.log(idVersion)
    
    $("#listas").dataTable().fnDestroy();
    $('#listas').hide()
    
    getItemsEvaluation(idLista)
    
});

function getItemsEvaluation(id){
    
    let datos = {
        id:id
    }
    
       $.ajax({
        type: 'POST',
        async: false,
        data: datos,
        url: "./getItemsList",
        success: function (data) {

            console.log(data)
            drawItems(data)
            
        },
        error: function (data) {

            console.log(data)

        }

    })
    
}

function drawItems(data){
    
    let str = ``
    let div = document.getElementById('calificacion')
    
    str += `<div class="row">
                            <div class="col-md-5">
                                Descripción del item
                            </div>
                            <div class="col-md-2">
                                <div class="pl-5">
                                    Cumple
                                </div>
                            </div>
                            <div class="col-md-5">
                                  Observaciones
                            </div>
                        </div>`
    
    for(var item of data){
        
        str += `<div class="row pt-4">
                            <div class="col-md-6">
                                ${item.nombre}
                            </div>
                            <div class="col-md-1">
                                <div class="pt-2 align-middle">
                                    <input type="checkbox" aria-label="Checkbox for following text input" value="${item.idListaFK}">
                                </div>
                            </div>
                            <div class="col-md-5">
                                  <textarea id="${item.idListaFK}" class="form-control" name="descripcion" id="descripcion_oa" rows="3" placeholder="Escriba una observación"></textarea>
                            </div>
                        </div>
                        <hr>
                        `
    }
    
    str += `<div class="row justify-content-md-center">

                                    <div class="col-md-auto">
                                        <p>El contenido apueba</p>
                                    <div class="form-check">
                                        <input class="checkbox" id="rn" name="aprueba" type="radio" value="on">
                                        <label class="form-check-label pl-1" for="defaultCheck3">Si</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="checkbox" id="rn" name="aprueba" type="radio" value="off">
                                        <label class="form-check-label pl-1" for="defaultCheck3">No</label>
                                    </div>
                                       
                                    </div>

                                </div>`
    
    str += `<div class="row pt-4">
                        <p>Observación</p>
                            <div class="col-md-12">
                                  <textarea id="" class="form-control" name="descripcion" id="descripcion_oa" rows="3" placeholder="Escriba una observación general"></textarea>
                            </div>
            </div>`
    
    div.innerHTML =str
    
}