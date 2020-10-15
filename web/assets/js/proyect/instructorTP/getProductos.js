$(function () {

    let menu = document.getElementById('getProducts')
    menu.classList.add('active')
    queryProducts()

})

function queryProducts() {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./ProductosVirtuales",
        success: function (data) {

            generateTable(data)

        }

    })

}

function generateTable(data) {

    let idUser = document.getElementById('webpagelink').value

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

    $('#example').dataTable({
        "processing": false,
//        "responsive": true,
        "order": [[3, "desc"]],
        "aaData": arrayF,
        "columns": [
            {
                "mData": "nombre",
//                "className": "bill-row", 
                "mRender": function (data, type, row) {
                    if (data.length <= 50) {
                        return data.toString().substr(0, 50);
                    } else {
                        return data.toString().substr(0, 50) + '...';
                    }
                }
            },
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
                    return `<a target="_blank" class="btn btn-info" href="${data.url}" download="">Descargar P.V</a>`;
                }
            },
            {
                "mData": "versioDTO",
                "mRender": function (data, type, row) {
                    return `<button type="button" id="${data.idVersion}" class="btn btn-info btnEvaluar" value="${row.nombre}">Evaluar P.V</button>`;
                },
            },
        ],
//        createdRow: function (row, data, index) {
//        console.log(row)
//        $(row).addClass("text-justify");
//        
//    },
        "language": {
            "lengthMenu": "Mostrar _MENU_ x página",
            "zeroRecords": "No encontrados",
            "info": "página _PAGE_ de _PAGES_",
            "infoEmpty": "No hay elementos",
            "infoFiltered": "(total _MAX_ )"
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


        }
    })

    return datos

}
