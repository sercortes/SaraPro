
$(document).on('click', '.sendEvaluation', function (e) {

    $(".remove").remove();

    let aprobados = $("input[type='radio']:checked").val()
    let observacion = $('#observacion').val()

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
        swal('', 'Apruebe todos los items!', "info")
        $('#Aprueba').prop('checked', false)
        return false
    }

    if (noAprobado && resultado === 1) {
        swal('', 'Opción incorrecta, todos los items cumplen!', "info")
        $('#noAprueba').prop('checked', false)
        return false
    }

    console.log('enviar')

    console.log(itemsE)
    console.log(resultado)
    console.log(aprobados)
    console.log(observacion)

    let data = {
        'resultado': resultado,
        'idLista': idLista,
        'version': idVersion,
        'observacion': observacion,
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

            console.log(datas)

            $('#cargas').removeClass('is-active');

            if (datas) {
                swal('', 'Evaluado', "success").then((value) => {

                    location.reload()
                });
                ;
            } else {
                swal('', 'Upsss', "error").then((value) => {

                    location.reload()

                });
                ;
            }

        }, error: function (error) {

            location.reload()

        }
    })

}