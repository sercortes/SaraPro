
$(document).on('click', '.sendEvaluation', function (e) {

    $(".remove").remove();

    let aprobados = $("input[type='radio']:checked").val()
    let observacion = $('#observacion').val()
    let fechaLimite = document.getElementById('fechaLimite').value

    let itemsE = []
    let cc = 0
    let resultado = 3

    $("input:checkbox:checked").each(function () {
        let campo = $(this).val();
        if (campo !== "on") {
            var item = {
                idDetallesLista: campo,
                observacion: $("#" + campo).val(),
                valoracion: 1,
            }
            itemsE.push(item)
        }
    });

    $("input:checkbox:not(:checked)").each(function () {
        let campo = $(this).val();
        if (campo !== "on") {
            var item = {
                idDetallesLista: campo,
                observacion: $("#" + campo).val(),
                valoracion: 0,
            }
            itemsE.push(item)
            cc++;
        }
    });

    if (cc > 0) {
        resultado = 0
    } else {
        resultado = 1
    }

    let aprobado = $('#Aprueba').prop('checked')
    let noAprobado = $('#noAprueba').prop('checked')

    if (!aprobado && !noAprobado) {
        $('#tituloAprobar').focus().after("<div class='remove'><font color='red'>Califique el p.v</font><div>")   
        swal('', 'Califique el producto virtual!', "info")
        return false
    }

    if (observacion == '' || observacion.length <= 3) {
        swal('', 'Agregue una observación!', "info")
        $('#observacion').focus().after("<div class='remove'><font color='red'>Por favor ingrese una observación</font><div>")   
        return false;
    }

    if (aprobado && resultado === 0) {
        swal('', 'Por favor ¡Apruebe todos los items!', "info")
        $('#Aprueba').prop('checked', false)
        return false
    }

    if (noAprobado && resultado === 1) {
        swal('', 'Opción incorrecta, todos los items cumplen!', "info")
        $('#noAprueba').prop('checked', false)
        return false
    }
    
    if (noAprobado && fechaLimite === '') {
        $('#tituloAprobar').focus().after("<div class='remove'><font color='red'>Fecha Limite</font><div>")   
        swal('', '¡Por favor, Selecione una fecha limite!', "info")
        return false
    }
    
    if (aprobado && fechaLimite === '') {
        fechaLimite = 'No'
    }

    let fechaActual = new Date().getTime()
    let fechaIngresada = new Date(fechaLimite).getTime()

    if (fechaIngresada<=fechaActual) {
        $('#tituloAprobar').focus().after("<div class='remove'><font color='red'>Seleccione una fecha válida</font><div>")   
        swal('', 'Selecione una fecha válida!', "info")
        return false
    }
    
//    let fechaActualTwo = new Date().getTime()+604800000
   let today = new Date()
   let fechaActualTwo = new Date(today.getFullYear(), today.getMonth(), today.getDate()+3);

    if (!aprobado && fechaIngresada<=fechaActualTwo) {
        $('#tituloAprobar').focus().after("<div class='remove'><font color='red'>Recuerde dejar mínimo 3 días para la correción</font><div>")   
        swal('', '¡Recuerde, son 3 días mínimos para la correción!', "info")
        return false
    }
    
    
    let data = {
        'resultado': resultado,
        'idLista': idLista,
        'version': idVersion,
        'observacion': observacion,
        'fechaLimite': fechaLimite,
        'detallesEvaluacion': JSON.stringify(itemsE)
    }

       send(data)

       $('#evaluate').attr('disabled',true)
       $('#cargas').addClass('is-active');

})

function send(data) {

    $.ajax({
        type: 'POST',
        url: './Evaluate',
        dataType: "json",
        data,
        success: function (datas) {

            $('#cargas').removeClass('is-active');

            if (datas === 1) {
                swal('', 'Evaluado', "success").then((value) => {

                    location.reload()
                });
                ;
            } else if(datas === 2) {
                swal('', 'Upsss', "error").then((value) => {

                    location.reload()

                });
                ;
            }else if(datas === 32) {
                swal('', 'Producto ya ha sido evaluado', "info").then((value) => {

                    location.reload()

                });
                ;
            }

        }, error: function (error) {

            location.reload()

        }
    })

}

$(document).on('click', '#noAprueba',function(e){
    
    let checkBox = document.getElementById('noAprueba')
    
    if (checkBox.checked) {
        $('#fechaDiv').show();
    }
    
})

$(document).on('click', '#Aprueba',function(e){
    
    let checkBox = document.getElementById('Aprueba')
    
    if (checkBox.checked) {
        $('#fechaDiv').hide()
    }
    
})