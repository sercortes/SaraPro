var idVersion, idLista

$(document).on('click', '.btnEvaluar', function (e) {

    let nombre = this.value
    idVersion = this.id;

    getListas();

    $('#modalC').modal({
        backdrop: 'static',
        keyboard: false
    })
    $('#modalTittle').text(nombre)

    $('#modalDetails').modal('hide')
    $('#modalC').modal('show')
    
    $('#contenedorCalificacion').hide()
    
    $('#listas').show()
    $('#crearLista').show()
    
    $('#fechaDiv').hide()

});

//$('#modalC').on('hide.bs.modal', function(e){
//    console.log('restart')
//})

function getListas() {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getList",
        success: function (data) {

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
                    "zeroRecords": "No hay elementos",
                    "info": "página _PAGE_ de _PAGES_",
                    "infoEmpty": "No hay elementos, cree una lista de chequeo",
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

    let idListas = this.value
    idLista = idListas
    
    $('#listas').DataTable().clear().destroy();
    $('#listas').hide()
    $('#contenedorCalificacion').show()
    $('#crearLista').hide()
    getItemsEvaluation(idLista)

});

function getItemsEvaluation(id) {

    let datos = {
        id: id
    }

    $.ajax({
        type: 'POST',
        async: false,
        data: datos,
        url: "./getItemsList",
        success: function (data) {

            drawItems(data)

        },
        error: function (data) {

            console.log(data)

        }

    })

}

function drawItems(data) {

    let str = ``
    let div = document.getElementById('calificacion')

    for (var item of data) {

//        str += `<div class="row pt-4">
//                            <div class="col-md-6">
//                                ${item.nombre}
//                            </div>
//                            <div class="col-md-1">
//                                <div class="pt-2 align-middle">
//                                    <input type="checkbox" aria-label="Checkbox for following text input" value="${item.idListaFK}">
//                                </div>
//                            </div>
//                            <div class="col-md-5">
//                                  <textarea id="${item.idListaFK}" class="form-control" name="descripcion" id="descripcion_oa" rows="3" placeholder="Escriba una observación"></textarea>
//                            </div>
//                        </div>
//                        <hr>`

        str += `<tr class="">
                    <th class="" style="width:45% ;">${item.nombre}</th> 
                    <th class="text-center" style="width:10% ;"><input type="checkbox" aria-label="Checkbox for following text input" value="${item.idListaFK}" class=""></th>
                    <th class="" style="width:45% ;"><textarea id="${item.idListaFK}" class="form-control" name="descripcion" id="descripcion_oa" rows="3" placeholder="Escriba una observación"></textarea></th>
                        </tr>`



    }

   

    div.innerHTML = str

}