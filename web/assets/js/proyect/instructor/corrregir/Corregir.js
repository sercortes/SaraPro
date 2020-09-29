var idEv, idver

$(function(){
    
    let menu = document.getElementById('Corregir')
    menu.classList.add('active')
    queryCorrecion()
    $('#eva').hide()
    
})


function queryCorrecion() {

    $.ajax({
        type: 'POST',
        async: false,
        url: "./getNotificationsCorre",
        success: function (datas) {

            console.log(datas)
            
             $('#example').dataTable({
                "processing": true,
                "aaData": datas,
                "columns": [
                    {"data": "nombre"},
                    {"data": "versioDTO.numVersion"},
                    {"data": "evaluacionDTO.FechaEvaluacion"},
                    {"data": "evaluacionDTO.nomFuncionario"},
                    {"data": "evaluacionDTO.resultado"},
                    {
                        "mData": "versioDTO",
                        "mRender": function (data, type, row) {
                            return ` <a target="_blank" class="btn btn-info" href="${data.url}" download="">Descargar P.V</a>`;
                        }
                    },
                    {
                        "mData": "versioDTO","mData": "evaluacionDTO", "mData":"nombre",
                        "mRender": function (data, type, row) {
                            return `<button nombreP="${row.nombre}" evaluador="${row.evaluacionDTO.nomFuncionario}" 
                                     idLista="${row.evaluacionDTO.idListaChequeoFK}" type="button" id="${row.evaluacionDTO.idEvaluacion}" 
                                    value="${row.versioDTO.idVersion}" class="btn btn-info btnCorre">Corregir</button>`;
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

        }

    })
    
    }
    
    $(document).on('click', '.btnCorre', function (){
    
    $('#tabla').hide()
    $('#eva').show()
    
    let idE = this.id
    idver = this.value
    
    idEv = idE
    
    let nombreP = this.getAttribute('nombreP')
    let evaluador = this.getAttribute('evaluador')
    let idListas = this.getAttribute('idLista')
   
    let data = {
        idEva:idE
    }
    
    $.ajax({
        type: 'POST',
        async: false,
        data: data,
        url: "./getEvaluation",
        success: function (data) {

            tittle(data, nombreP, evaluador)
            getItemsEvaluation(idListas)
            
        },
        error: function (data) {

            console.log(data)

        }

    })
    
    
})



function getItemsEvaluation(id){
    
    console.log(id)
    
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
            if (data.length <= 0) {
                queryEmphy()
                return false
            }
            
            generateItems(data)
            
        },
        error: function (data) {

            console.log(data)

        }

    })
    
}

function generateItems(datos){
    let form = document.getElementById('Respuestaitem')
    let str = ``
    for(var item of datos){
        str +=`<label class="" id="">${item.nombre}</label>
                    <div class="pb-2" id="">
                        <textarea id="" class="form-control" cols="60" rows="4" readonly="true">`
        
        str += getItemsCalificados(idEv, item.idListaFK);
        
        str +=`</textarea>
                    </div>
            `
    }
    form.innerHTML = str   
}

function getItemsCalificados(idEvaluacion, idItemLista){
    
    let data ={
        idEvaluacion:idEvaluacion,
        idItem:idItemLista
    }
    
    let datos = ``
    
    $.ajax({
        type: 'POST',
        async: false,
        data: data,
        url: "./getItemsCalificados",
        success: function (data) {
            
            if (data.length !== 0) {
                datos = data[0].idItem
            }
           
        },
        error: function (data) {


        }

    })
    
    return datos
    
}

$(document).on('click', '#SendFile', function () {

    let file = document.getElementById('newFile').value

    if (file == '' || file == undefined) {
        swal('Ok', 'Seleccione un archivo', "info");
        return false;
    }

    $('#SendFile').attr('disabled', true);
    $('#cargas').addClass('is-active');

    var form = $('#files')[0]
    var data = new FormData(form)

    updateServlet(data, idver, 'versionP')

})

