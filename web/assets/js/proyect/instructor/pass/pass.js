$('#changePass').on('click', function(){
    
    let passOne = document.getElementById('passOne').value
    let passTwo = document.getElementById('passTwo').value
     
    if (passOne == '' || passTwo == '') {
        messageInfo('Espera!', 'Completa el formulario')
        return false
    }
    if (passOne !== passTwo) {
        messageInfo('Espera!', 'las contraseñas no coinciden')
        return false
    }
    
    if (passOne.length <=7 || passTwo.length <=7) {
        messageInfo('Espera!', 'Crea una contraseña más segura')
        return false
    }
    
    let data ={
        pass: passOne
    }
    
    $('#changePass').prop('disabled', true)
    changePass(data)
    
})

function changePass(data){
    
    $.ajax({
        type: 'POST',
        url: "./updatePassword",
        data: data,
        success:function(data){
            if (data) {
                messageSuccess('','Contraseña Cambiada')
            }else{
                messageError('','Ups')
            }
        },
        error:function(data){
            console.log(data)
        }
    })
    
    $('#changePass').prop('disabled', false)
    $('#formularioPass').trigger('reset')
    
}


function messageInfo(title, message){
        swal(title, message, "info");
}

function messageSuccess(title, message){
        swal(title, message, "success");
}

function messageError(title, message){
        swal(title, message, "error");
}


$(".reveal").on('click',function() {
    var $pwd = $(".pwd");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
});