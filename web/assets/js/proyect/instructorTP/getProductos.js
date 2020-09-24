$(function(){
    
    let menu = document.getElementById('getProducts')
    menu.classList.add('active')
    queryProducts()
    
})

function queryProducts() {

  let idUser = document.getElementById('webpagelink').value

    $.ajax({
        type: 'POST',
        async: false,
        url: "./ProductosVirtuales",
        success: function (data) {

            console.log(data)
            
            let arrayF = []

            for (var item of data) {
                let bande = 0
                item.autores = []
                let arra = getAutores(item.versioDTO.idVersion)
                for (var au of arra) {
                    item.autores.push(au)
                    if (au.idFuncionario == idUser) {
                        bande = 1
                    }
                }
                if (bande == 0) {
                    arrayF.push(item)
                }
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
                    {"data": "palabrasClave"},
                    {"data": "versioDTO.fechaEnvio"},
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
