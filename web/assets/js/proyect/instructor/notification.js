$(function () {

    let menu = document.getElementById('notification')
    menu.classList.add('active')
    queryNotification()

})

function queryNotification() {

    $.ajax({
        type: 'POST',
        async: false,
        url: "./getNotificationsGeneral",
        success: function (data) {
            
            $('#example').dataTable({
                "processing": true,
                "order": [[ 0, "ASC" ]],
                "aaData": data,
                "columns": [
                    {"data": "FechaEnvio"},
                    {"data": "descripcionNotificacion"},
                    {"data": "productoVirtualDTO.nombre"}
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

