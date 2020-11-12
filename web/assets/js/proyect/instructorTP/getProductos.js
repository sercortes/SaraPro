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

    $('#example').dataTable({
//        "processing": true,
//        
        "orderClasses": false,
        "deferRender": true,
//        "serverSide": false,
          
//        "responsive": true,
//        "order": [[3, "desc"]],
        "order": [],
        "aaData": data,
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
                "mData": "versioDTO.autores",
            },
            {"data": "palabrasClave"},
            {"data": "versioDTO.fechaEnvio"},
//            {
//                "mData": "versioDTO",
//                "mRender": function (data, type, row) {
//                    return `<a target="_blank" class="btn btn-info" href="${data.url}" download="">Descargar P.V</a>`;
//                }
//            },
            {
                "mData": "versioDTO",
                "mRender": function (data, type, row) {
//                    return `<button type="button" id="${data.idVersion}" class="btn btn-info btnEvaluar" value="${row.nombre}"><i class="material-icons">add_task</i> Ver P.V</button>`;
                    return `<button type="button" id="${data.idVersion}" class="btn btn-info btnDetails" value="${row.nombre}"><i class="material-icons">notes</i> Ver P.V</button>`;
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
