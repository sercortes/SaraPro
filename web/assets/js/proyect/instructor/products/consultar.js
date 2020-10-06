$(function () {

    let menu = document.getElementById('consultar')
    menu.classList.add('active')
    queryProducts()

})


function queryProducts() {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./Productos",
        success: function (data) {

            console.log(data)

            let arrayF = []

            for (var item of data) {
                item.autores = []
                let arra = getAutores(item.versioDTO.idVersion)
                for (var au of arra) {
                    item.autores.push(au)
                }
                arrayF.push(item)
            }

            console.log(arrayF)

            $('#example').dataTable({
                "processing": false,
                "aaData": arrayF,
                "columns": [
                    {"data": "nombre"},
                    {
                        "mData": "autores",
                        "mRender": function (data, type, row) {
                            let datos = ''
                            for (var item of data) {
                                datos += item.nomFuncionario + ' ' + item.apeFuncionario + ', '
                            }
                            return datos;
                        }
                    },
                    {"data": "palabrasClave"},
                    {"data": "versioDTO.fechaPublicacion"},
                     {
                        "mData": "versioDTO",
                        "mRender": function (data, type, row) {
                            return ` <a target="_blank" class="btn btn-info" href="${data.url}" download="">Descargar P.V</a>`;
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

        }

    })

}

function getAutores(id) {

    let datos = []

    $.ajax({
        type: 'POST',
        async: false,
        url: "./getAutoresPro",
        data: {
            id: id
        },
        success: function (data) {

            datos = data
        },
        error: function (data) {
            console.log(data)
        }
    })

    return datos

}