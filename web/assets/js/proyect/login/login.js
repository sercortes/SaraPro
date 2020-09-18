
$(document).on('click','#buttonEnter', function(e){
    
    e.preventDefault()
    
    $(".remove").remove();

    let emails = document.getElementById('inputEmail').value
    let passw = document.getElementById('inputPassword').value

    if(!validateEmail(emails) || emails.length <= 5){
        $('#inputEmail').focus().after("<div class='remove'><font color='red'>Ingrese un correo válido</font><div>")
        return false
    }

    if(passw == '' || passw.length <= 5){
        $('#inputPassword').focus().after("<div class='remove'><font color='red'>Ingrese una clave válida</font><div>")   
        return false
    }

    let data ={
        user:emails,
        pass:passw
    }
    
    enter(data)

})

function enter(data){
    
    $.ajax({
        url: "./Start",
        type: 'POST',
        data: data,
        success: function (data) {
            
            if (data !== 0) {
                
                let datos = getRoles(data)
          
                if (datos.length == 1) {
                    
                    window.location.replace('./setRolOne') 
                    
                }else{
                    
                    window.location.replace('./Home')    
                    
                }

            } else {
                
                window.location.replace('index.jsp')    
                
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