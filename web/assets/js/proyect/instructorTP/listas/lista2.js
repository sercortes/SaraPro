$(document).on('click', '#send', function (e){
    
    e.preventDefault()
    $(".remove").remove();
    
    let arregloItem = $('select#SelectItems').val()
    let nombre = $('#nombreLista').val()
    let descripcion = $('#descipcionLista').val()
    
        if (nombre == '' || nombre == undefined) {
            $('#nombreLista').focus().after("<div class='remove'><font color='red'>Falta Nombre</font><div>")   
            return false
        }
         if (descripcion == '' || descripcion == undefined) {
            $('#descipcionLista').focus().after("<div class='remove'><font color='red'>Falta Descripción</font><div>")   
            return false
        }
    
    
      let data = {
            nombre:nombre,
            descripcion:descripcion
        }

        arregloItem = arregloItem.sort()

         $('#cargas').addClass('is-active')
         $('#send').attr('disabled', true)
                
        createList(data, arregloItems)
    
})


 function createList(data, arre){
        
          $.ajax({
        type: "POST",
        url: "CreateList?items=" + arre,
        data: data,
        success: function (data) {

                $('#cargas').removeClass('is-active')
                $('#send').attr('disabled', false)

                if (data!==0) {
                    swal('', 'Lista creada', "success").then((value) => {
                               $('#SelectItems').multiSelect('deselect_all');
                                $('#formListas')[0].reset();
//                                location.reload()
                    });
                    ;
                } else {
                    swal('', 'Upsss', "error").then((value) => {
                                $('#SelectItems').multiSelect('deselect_all');
                                $('#formListas')[0].reset();
//                                location.reload()
                    });
                    ;
                }
          
          
        },
        error: function (e) {

            console.log(e)

        }
    });

        
    }