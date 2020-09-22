$(function () {

    let menu = document.getElementById('consultar')
    menu.classList.add('active')
    queryProducts()

})


function queryProducts() {

    $.ajax({
        type: 'POST',
        async: false,
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
                "processing": true,
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
                    {"data": "descripcion"},
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