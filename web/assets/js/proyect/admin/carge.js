$(document).on('click', '#botonModal', function(e){
    e.preventDefault()
    
     $('#modalC').modal({
        backdrop: 'static',
        keyboard: false
    })

    $('#modalC').modal('show')
    
})

$(document).on('click', '#botonCargeMas', function (e) {

    e.preventDefault()

    let file = document.getElementById('files').value

    let isInstructor = $('#Instructors').is(":checked")
    let isTecnico = $('#tecnicos').is(":checked")
    let isPedago = $('#pedagos').is(":checked")

    let centroFormacion = document.getElementById('centroFormacionTwo').value;
    let area = document.getElementById('reddeconocimientoTwo').value;

    let roles = []

    $("input:checkbox[name=rols]:checked").each(function () {
        roles.push($(this).val())
    });

    if (file == '' || file == undefined) {
        swal('Ups', 'Selecione un archivo', "info");
        return false
    }

    if (!isInstructor && !isTecnico && !isPedago) {
        swal('Ups', 'Selecione un rol', "info");
        return false
    }

    if (area == '') {
        swal('Ups', 'Selecione un área', "info");
        return false
    }
    if (centroFormacion == '') {
        swal('Ups', 'Selecione un centro', "info");
        return false
    }

    console.log(area)
    console.log(centroFormacion)

    var form = $('#formMasivo')[0]
    var data = new FormData(form)

    console.log(roles)
    console.log('send')
    enviarServlet(data, roles, area, centroFormacion)

    $('#botonCargeMas').attr('disabled', true);
    $('#cargas').addClass('is-active');

})

function enviarServlet(data, roles, area, centro) {

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "RegisterManyUsers?roles=" + roles + '&area=' + area + '&centro=' + centro,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            
            if (data == '1') {

                reload('Los usuarios han sido registrados', 'success')

            } else if (data == '2') {

                reload('Archivo no válido', 'info')

            } else if (data == '3') {

                reload('Existen datos nulos', 'info')

            } else if (data === '4') {

                reload('Usuarios ya registrados', 'info')

            } else if (data == '5') {

                reload('Error sql', 'info')

            } else if (data == '6') {

                reload('Exception', 'info')

            }


//            $('#botonCargeMas').attr('disabled', false);
            $('#cargas').removeClass('is-active');

        },
        error: function (e) {

            console.log(e)

        }
    });


    function reload(mensaje, estado) {

        swal('', mensaje, estado).then((value) => {

            location.reload()
        });


    }

}