
$(document).on('click', '#botonRoles', function () {

    let array = $('select#selectRoles').val()

    if (!change) {
        swal('Ok', 'Por favor, realize algún cambio', "info");
        return false
    }
    if (array.length === 0) {
        swal('Ok', 'Por favor, ingrese por lo menos un rol', "info");
        return false
    }

    $('#cargas').addClass('is-active');
    updateRol(array, idUser)

})

function updateRol(array, idUser) {


    $.ajax({
        type: "POST",
        url: "UpdateRoles?roles=" + array,
        data: {
            idUser: idUser
        },
        success: function (data) {
           
            $('#cargas').removeClass('is-active');
            change = false;
            if (data === '1') {
                 swal('Ok', 'Operación realizada con éxito', "success");
            }else if (data === '0'){
                 swal('Ok', 'Error', "info");
            }

        },
        error: function (e) {

            console.log(e)

        }
    });

}