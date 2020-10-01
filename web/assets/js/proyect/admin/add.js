$(function(){
    
    let menu = document.getElementById('addUser')
    menu.classList.add('active')
    
      tipoDocumento()
    getCentros()
    getAreas()
    
})

$("#boton1").click(function () {
    $(".remove").remove();
    var boo = 0;
    var inputs = $(".input");
    var selec = $(".select");
    var input, selet;

    for (var i = 0; i < selec.length; i++) {
        if (selec[i].value === "") {
            selet = $(selec[i]);
            selet.focus().after("<div class='remove'><font color='red'>seleccione una opcion</font><div>");
        } else {
            boo++;
        }
    }

    let nombre = document.getElementById('nombre').value;
    let apellido = document.getElementById('apellido').value;
    let tipoIdenti = document.getElementById('tipoIdenti').value;
    let numeroIdentificacion = document.getElementById('numeroIdentificacion').value;
    let email = document.getElementById('email').value;
    let centroFormacion = document.getElementById('centroFormacion').value;
    let area = document.getElementById('reddeconocimiento').value;
    let ipSena = document.getElementById('ipSena').value;
    let cargo = document.getElementById('cargo').value;

    let isInstructor = $('#Instructor').is(":checked")
    let isTecnico = $('#tecnico').is(":checked")
    let isPedago = $('#pedago').is(":checked")
    let isCoor = $('#coordina').is(":checked")
    
    let data = {
        nombre:nombre,
        ape:apellido,
        tipoIden:tipoIdenti,
        id:numeroIdentificacion,
        email:email,
        centro:centroFormacion,
        area:area,
        numero:ipSena,
        cargo:cargo
    }
    
    let roles = []
    

$("input:checkbox[name=rol]:checked").each(function(){
    roles.push($(this).val())
});


    console.log(data)
    console.log(roles)

    if (!isInstructor && !isTecnico && !isPedago && !isCoor) {
        $('#tipoUsu').focus().after("<div class='remove'><font color='red'>Seleccione un rol</font><div>");
        return false
    }

    if (nombre == "" || nombre.length <= 2) {
        $('#nombre').focus().after("<div class='remove'><font color='red'>Digite un nombre</font><div>");    
        return false
    }

    if (apellido == "" || apellido.length <= 2) {
        $('#apellido').focus().after("<div class='remove'><font color='red'>Digite un apellido</font><div>");    
        return false
    }

    if (numeroIdentificacion == "" || numeroIdentificacion.length <= 2) {
        $('#numeroIdentificacion').focus().after("<div class='remove'><font color='red'>Digite un número de identificación</font><div>");    
        return false
    }

    if (email == "" || email.length <= 2) {
        $('#email').focus().after("<div class='remove'><font color='red'>Digite el correo</font><div>");    
        return false
    }

    if (!validateEmail(email)) {
        $('#email').focus().after("<div class='remove'><font color='red'>Ingrese un correo válido</font><div>");    
        return false
    }


    if (ipSena == "" || ipSena.length <= 2) {
        $('#ipSena').focus().after("<div class='remove'><font color='red'>Digite ip SENA</font><div>");    
        return false
    }

    if (cargo == "" || cargo.length <= 2) {
        $('#cargo').focus().after("<div class='remove'><font color='red'>Digite el cargo</font><div>");    
        return false
    }


    if (isInstructor && isCoor) {
        $('#tipoUsu').focus().after("<div class='remove'><font color='red'>Seleccione roles válidos</font><div>");
        cleanCheck()
        return false
    }

    if (isTecnico && isCoor) {
        $('#tipoUsu').focus().after("<div class='remove'><font color='red'>Seleccione roles válidos</font><div>");
        cleanCheck()
        return false
    }

    if (isPedago && isCoor) {
        $('#tipoUsu').focus().after("<div class='remove'><font color='red'>Seleccione roles válidos</font><div>");
        cleanCheck()
        return false
    }

    if (boo !== 3) {
        return false
    }

    console.log('enviar')
    
    $('#cargas').addClass('is-active');
    $('#boton1').prop('disabled', true);
    
    $.ajax({
        type: "POST",
        url: "newUser?roles=" + roles,
        data: data,
        success: function (data) {

            if (data === 1) {
                swal('Ok', 'El usuario ha sido registrado', "success");
            }else if(data === 2){
                swal('Ups', 'Este usuario ya ha sido registrado, correo o documento repetidos', "info");
            }else if (data === 3) {
                swal('Error', 'Error', "error");
            }
            
            
            $('#formProduct').trigger('reset')
            $('#formMasivo').trigger('reset')
            $('#cargas').removeClass('is-active');
            $('#boton1').prop('disabled', false);

        },
        error: function (e) {

              $('#formProduct').trigger('reset')
              $('#formMasivo').trigger('reset')
            $('#cargas').removeClass('is-active');
            $('#boton1').prop('disabled', false);

        }
    });

});

function cleanCheck() {

    $("#Instructor").prop("checked", false);
    $("#tecnico").prop("checked", false);
    $("#coordina").prop("checked", false);
    $("#pedago").prop("checked", false);

}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}



function tipoDocumento() {
    $.ajax({
        type: 'POST',
        url: './SelectsTipoDocu',
        success: function (data) {

            let categoria = document.getElementById('tipoIdenti');
            let str = `<option value="">Seleccionar...</option>`

            for (var item of data) {
                str += `<option value="${item.idDocumento}">${item.nombre}</option>`
            }

            categoria.innerHTML = str;
        },
        error: function (data) {
            console.log(data)
        }
    })

}

function getCentros() {
    $.ajax({
        type: 'POST',
        url: './SelectsCentros',
        success: function (data) {

            let categoria = document.getElementById('centroFormacion');
            let str = `<option value="">Seleccionar...</option>`

            for (var item of data) {
                str += `<option value="${item.idCentro}">${item.nombreCentro}</option>`
            }

            categoria.innerHTML = str;
            
            let centro = document.getElementById('centroFormacionTwo');
       

            centro.innerHTML = str;
            
        },
        error: function (data) {
            console.log(data)
        }
    })

}

function getAreas() {
    $.ajax({
        type: 'POST',
        url: './SelectsArea',
        success: function (data) {

            let categoria = document.getElementById('reddeconocimiento');
            let str = `<option value="">Seleccionar...</option>`

            for (var item of data) {
                str += `<option value="${item.idArea}">${item.nomArea}</option>`
            }

            categoria.innerHTML = str;
            
            let areas = document.getElementById('reddeconocimientoTwo');
        
            areas.innerHTML = str;
            
        },
        error: function (data) {
            console.log(data)
        }
    })

}