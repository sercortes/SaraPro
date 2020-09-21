$(document).on('click', '#send', function (e) {

    if (!validate(e)) {
        return false
    }

    let derechos = $("input[name='derechosAutor']")
    let dere = ''

    for (let item of derechos) {
        if (item.checked) {
            dere = item.defaultValue
        }
    }

    var form = $('#formProducts')[0]
    var data = new FormData(form)

    let arregloCategorias = $('select#MultiCategoriass').val()
    let arregloAutores = $('select#SelectAutores').val()

    console.log('enviar')
    console.log(arregloAutores)
    console.log(arregloCategorias)

//    $('#send').attr('disabled', true);
//    $('#cargas').addClass('is-active');

    enviarServlet(data, arregloCategorias, arregloAutores)   


})


function enviarServlet(data, categorias, autores) {

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "uploadProduct?categorias=" + categorias + "&autores=" + autores,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {

            console.log(data)
            console.log(typeof data)
            console.log(data == '1')
            if (data == '1') {
                swal('', 'El producto ha sido creado', "success").then((value) => {
                             location.reload()
                    });
               
            } else if (data == '0') {

                $.notify(":(", "error");
            }

            $('#UploadForm').trigger('reset')
            $('#subir_oas').attr('disabled', false);
            $('#cargas').removeClass('is-active');

        },
        error: function (e) {

            console.log(e)

        }
    });

}



document.getElementById('formProduct').addEventListener('change', e => {

    e.preventDefault()
     if (!validate(e)) {
        return false
    }

})

function validate (e){
    
    e.preventDefault()
    $(".remove").remove();
    let des = document.getElementById('descripcion_oa').value;
    let pal = document.getElementById('palabras_claves').value;
    let titulo = document.getElementById('Titulo_Publicacion').value;
    let requisitos = document.getElementById('requisitos_instalacion').value;
    let formato = document.getElementById('formato').value;
    let instru = document.getElementById('instrucciones').value;
    let file = document.getElementById('files').value
    let dac = $("input[name='derechosAutor']").is(':checked')

    if (titulo == '' || titulo.length == 0) {
        validar('Titulo_Publicacion', 'Falta el título del producto')
        return false;
    }
    if (des == '' || des.length == 0) {
        validar('descripcion_oa', 'Falta una descripción')
        return false;
    }
    if (pal == '' || pal.length == 0) {
        validar('palabras_claves', 'Falta las palabras clave')
        return false;
    }
    if (instru == '' || instru.length == 0) {
        validar('instrucciones', 'Falta las instruciones')
        return false;
    }
    if (requisitos == '' || requisitos.length == 0) {
        validar('requisitos_instalacion', 'Faltan los requisitos de instalación')
        return false;
    }
    if (formato == '' || formato.length == 0) {
        validar('formato', 'Selecione un formato')
        return false;
    }
   
    if ($('select#MultiCategoriass').val().length === 0) {
        validar('SelectCategoria', 'Faltan los temas')
        return false;
    }

    if (!dac) {
        validar('r', 'Selecione un derecho de autor')
        return false
    }

    if (file == '' || file == undefined) {
        validar('files', 'Selecione un archivo')
        return false
    }
    
    return true
    
}

function validar(id, mensaje) {
    $('#' + id).focus().after(`<div class='remove'><font color='red'>${mensaje}</font><div>`)
}
