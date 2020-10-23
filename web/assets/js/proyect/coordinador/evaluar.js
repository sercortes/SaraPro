var idVersion
$(document).on('click', '.btnEvaluar', function(e){
    
    $('#formCheck').show()
    
    let Nombre = this.value
    idVersion = this.id
    
    console.log(idVersion)
    console.log(Nombre)
    
     $('#modalC').modal({
        backdrop: 'static',
        keyboard: false
    })
    
    $('#modalTittle').text(Nombre)

    $('#modalDetails').modal('hide')
    $('#modalC').modal('show')

    
})

$('#modalC').on('hide.bs.modal', function(e){
    $('input:checkbox').removeAttr('checked')
    $('#observacion').val('')
    $(".remove").remove();
})

$(document).on('click', '#send', function(e){
    
    $(".remove").remove();
    e.preventDefault()
    
    let comment = $('#observacion').val()
    let aprobar = document.getElementById('aprobar').checked
    let rtec = document.getElementById('rtecnico').checked
    let rped = document.getElementById('rpedagogico').checked
     
    if (aprobar == false && rtec == false && rped == false) {
        swal('', 'por favor seleccione una acción!', "info")
        $('#estadoPV').focus().after("<div class='remove'><font color='red'>Califique el p.v</font><div>")   
        return false
    }
    
     if (comment == '' || comment == undefined) {
        swal('', 'por favor ingrese una observación!', "info")
        $('#observacion').focus().after("<div class='remove'><font color='red'>Califique el p.v</font><div>")
        return false
    }
    
     if (comment.length <= 3) {
        swal('', 'por favor ingrese una observación válida!', "info")
        return false 
    }
    
    let resultado = 0
    
    if (aprobar) {
        resultado = 1
    }
    
    let data = {
        comentario:comment,
        idVersion:idVersion,
        resultado:resultado,
        aprobado:aprobar,
        rtecnico:rtec,
        rpedago:rped
    }
    
    console.log(data)
    
         $('#cargas').addClass('is-active');    
         $('#send').attr('disabled', true);
    
        $.ajax({
            type: 'POST',
            url: './EvaluateCoor',
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
                
            }, error: (function(){
                swal('', 'error producto ya evaluado', "error").then((value) => {
                    
                    location.reload()
                    
                });
                
            })
        })
    
})