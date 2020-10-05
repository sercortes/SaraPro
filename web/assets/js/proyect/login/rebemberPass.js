document.getElementById('buttonPass').addEventListener('click', function () {

    let id = document.getElementById('iden').value

    if (id == '') {
        swal('Ok', 'Ingrese una identificación', "info");
        return false
    }
    if (id.length <= 6) {
        swal('Ok', 'Ingrese una identificación válida', "info");
        return false
    }
    
     if (id.length >= 25) {
        $('#iden').val('')
        swal('Ok', 'Ingrese una identificación válida', "info");
        return false
    }
    
     if (!allnumeric(id)) {
        $('#iden').val('')
        swal('Ok', 'Ingrese una identificación válida.', "info");
        return false
    }
    
    if (grecaptcha.getResponse().length == 0) {
         $('#mensajes').focus().after("<div class='remove'><font color='red'>Complete la captcha</font><div>")   
        return false
    }


    $('#buttonPass').attr('disabled', true);
    $('#cargas').addClass('is-active');

   let data = {
       iden:id
   }
   
   data.fallsr = 1;
   data.falls = 1;
   data.rec = grecaptcha.getResponse()
   data.fall = 1;
   data.fal = 1;
   
   sendIden(data)

})

function sendIden(datas){
     $.ajax({
        url: "./ForgotPass",
        type: 'POST',
        data: datas,
        success: function (data) {

            $('#cargas').removeClass('is-active');

            if (data == 1) {

                swal('', 'Te hemos enviado un correo para que restablezcas la contraseña', "success").then((value) => {

                    window.location.replace("index.jsp")

                });

            } else if(data == 2){

                swal('', 'Captcha incorrecto', "info").then((value) => {

                    window.location.replace("index.jsp")

                });

            }else if(data == 3){

                swal('', 'Usuario no existe', "info").then((value) => {

                    window.location.replace("index.jsp")

                });

            }else if(data == 4){

                swal('', 'Error', "info").then((value) => {

                    window.location.replace("index.jsp")

                });

            }
            



        }, error: function (data) {

            console.log(data)

        }
    })
}

function allnumeric(inputtxt)
   {
      var numbers = /^[0-9]+$/;
      if(inputtxt.match(numbers))
      {
      return true;
      }
      else
      {
      return false;
      }
   } 


