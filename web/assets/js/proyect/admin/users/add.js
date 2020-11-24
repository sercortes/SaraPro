var idUser

$(document).on('click', '.btnDetails', function (e) {

    idUser = this.id;
    $('#modalDetailsU').modal({
        backdrop: 'static',
        keyboard: false
    })
    
    $('#modalDetailsU').modal('show')

    getFuncionario(idUser)

});

function getFuncionario(idUser) {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getUserOne",
        data: {
            idUser: idUser
        },
        success: function (data) {

            informePV(data)

        },
        error: function (data) {


        }
    })

}

function informePV(data) {

    $('#name').html('<b>Nombre : </b>'+data.nomFuncionario)
    $('#surname').html('<b>Apellido : </b>' + data.apeFuncionario)
    $('#email').html('<b>Correo : </b>' + data.correo)
    $('#area').html('<b>Área : </b>' + data.area)
    $('#centro').html('<b>Centro : </b>' + data.centro)

    getRoles()
    
    
}

function getRoles() {


    $.ajax({
        type: 'POST',
        async: false,
        url: "./Roles",
        data: {
            idFuncionario: idUser
        },
        success: function (data) {
            
            for(var item of data){
                $('#selectRoles').append(`<option value="${item.idRol}" selected>${item.nombreRol}</option>`);
            }

        },
        error: function (data) {


        }
    })
   
     $('#selectRoles').multiSelect( 'refresh' );

}

    

