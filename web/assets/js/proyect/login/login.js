
$(document).on('click', '#buttonEnter', function (e) {

    e.preventDefault()

    $(".remove").remove();

    let emails = document.getElementById('inputEmail').value
    let passw = document.getElementById('inputPassword').value

    if (!validateEmail(emails) || emails.length <= 5) {
        $('#inputEmail').focus().after("<div class='remove'><font color='red'>Ingrese un correo válido</font><div>")
        return false
    }

    if (passw == '' || passw.length <= 5) {
        $('#inputPassword').focus().after("<div class='remove'><font color='red'>Ingrese una clave válida</font><div>")   
        return false
    }
    
    if (grecaptcha.getResponse().length == 0) {
         $('#mensajes').focus().after("<div class='remove'><font color='red'>Complete la captcha</font><div>")   
        return false
    }

    let data = {
        user: emails,
        pass: passw
    }

    data.fallsx = 1;
    data.falls = 1;

    $('#buttonEnter').attr('disabled', true)
    $('#cargas').addClass('is-active');

    data.rec = grecaptcha.getResponse()
    enter(data)

})

function enter(data) {

    $.ajax({
        url: "./Start",
        type: 'POST',
        data: data,
        success: function (data) {

            if (data != 0 && data != -1) {

                let datos = getRoles(data)

                if (datos.length == 1) {

                    window.location.replace('./setRolOne')

                } else {

                    window.location.replace('./Home')

                }

            } else if(data == -1){
                 $('#cargas').removeClass('is-active');
                swal('', 'Captcha incorrecto', "info").then((value) => {

                    window.location.replace('index.jsp')

                });
            }else {

                $('#cargas').removeClass('is-active');
                swal('', 'Usuario o contraseña incorrectos', "info").then((value) => {

                    window.location.replace('index.jsp')

                });

            }

        }, error: function (data) {

        }
    })


}

function getRoles(idFuncionario) {

    let dato = []

    $.ajax({
        url: "./Roles",
        type: 'POST',
        async: false,
        data: {
            idFuncionario: idFuncionario
        },
        success: function (data) {

            dato = data

        }, error: function (data) {

        }
    })

    return dato

}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}