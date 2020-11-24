$(function () {

    let menu = document.getElementById('Users')
    menu.classList.add('active')
    getUsers()


})

function getUsers(){
    
    
    $('#cargas').addClass('is-active');
    
      $.ajax({
        type: 'POST',
        async: true,
        url: "./getAllUsers",
        success: function (data) {

            $('#cargas').removeClass('is-active');
            generateTable(data)

        }

    })
    
}


function generateTable(data) {

    $('#example').dataTable({
        "orderClasses": false,
        "deferRender": true,
        "order": [],
        "aaData": data,
        "columns": [
            {
                "mData": "nomFuncionario",
            },
             {
                "mData": "apeFuncionario",
            },
             {
                "mData": "correo",
            },
             {
                "mData": "area",
            },
            {
                "mData": "centro", 
                "mRender": function (data, type, row) {
                    if (data.length <= 50) {
                        return data.toString().substr(0, 50);
                    } else {
                        return data.toString().substr(0, 50) + '...';
                    }
                }
            },
            {
                "mData": "idFuncionario",
                "mRender": function (data, type, row) {
                    return `<button type="button" id="${data}" class="btn btn-info btnDetails" value="${45}"><i class="material-icons">notes</i> Ver P.V</button>`;
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
