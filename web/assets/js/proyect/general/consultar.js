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

            generateTable(data)
          
        }

    })

}


function generateTable(data) {

    $('#example').dataTable({
        "processing": false,
        "order": [[3, "desc"]],
        "aaData": data,
        "columns": [
            {
                "mData": "nombre",
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
            {
                "mData": "versioDTO",
                "mRender": function (data, type, row) {
                    return `<button type="button" id="${data.idVersion}" class="btn btn-info btnDetails" value="${row.nombre}"><i class="material-icons">notes</i> Ver P.V</button>`;
                },
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
