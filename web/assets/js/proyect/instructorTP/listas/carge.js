

$(document).on('click', '#botonCargeMas', function (e) {

    e.preventDefault()

    let file = document.getElementById('files').value

    if (file == '' || file == undefined) {
        swal('Ups', 'Selecione un archivo', "info");
        return false
    }

    if (!ValidateSize(document.getElementById('files'))) {
         swal('Ups', 'Archivo muy grande, Selecione un elemento de máximo 3 MB', "info");
        return false
    }
    
    if (!checkextension()) {
        return false;
    }

    var form = $('#formMasivo')[0]
    var data = new FormData(form)

    enviarServlet(data)
    console.log('send')

    $('#botonCargeMas').attr('disabled', true);
    $('#cargas').addClass('is-active');

})

function enviarServlet(data) {

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "./CargueList",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            
            console.log(data)
            
            if (data == '1') {

                reload('La lista ha sido creada', 'success')

            } else if (data == '2') {

                reload('Archivo no válido', 'info')

            } else if (data == '3') {

                reload('Verifique nombre y descripción de la lista', 'info')

            }  else if (data == '4') {

                reload('Verifique los items estan demasiados cortos, o muy extensos', 'info')

            } else if (data == '5') {

                reload('Espera, existe una lista con el mismo nombre y descripción', 'info')

            } else if (data == '6') {

                reload('Espera, revisa los items debes de tener más de tres y menos de 50', 'info')

            } else if (data == '7') {

                reload('Espera, error', 'info')

            } 

            $('#cargas').removeClass('is-active');

        },
        error: function (e) {

            console.log(e)

        }
    });

}

  function reload(mensaje, estado) {

        swal('', mensaje, estado).then((value) => {

            location.reload()
        });


    }
    
  function checkextension() {
    var file = document.querySelector("#files");
    if (/\.(xls)$/i.test(file.files[0].name) === false) {
   swal('Ups', 'Seleccione un archivo válido', "info");
          return false
    } else {
        return true
    }
}

function ValidateSize(file) {
        var FileSize = file.files[0].size / 1024 / 1024; // in MB
        if (FileSize > 3) {
            return false;
        } else {
            return true;
        }
    }