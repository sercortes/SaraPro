var idUser
var change = false

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

    $('#name').html('<b>Nombre : </b>' + data.nomFuncionario)
    $('#surname').html('<b>Apellido : </b>' + data.apeFuncionario)
    $('#email').html('<b>Correo : </b>' + data.correo)
    $('#area').html('<b>Área : </b>' + data.area)
    $('#centro').html('<b>Centro : </b>' + data.centro)

    getRoles()


}

function getRoles() {

    $('#selectRoles').html('');
    var arre = []
    arre = roles(arre)

    $.ajax({
        type: 'POST',
        async: false,
        url: "./Roles",
        data: {
            idFuncionario: idUser
        },
        success: function (data) {

            for (var item of data) {
                $('#selectRoles').append(`<option value="${item.idRol}" selected>${item.nombreRol}</option>`);
                arre = arre.filter(val => val.id != item.idRol);
            }

            for (var item of arre) {
                $('#selectRoles').append(`<option value="${item.id}">${item.nombre}</option>`);
            }

        },
        error: function (data) {


        }
    })

    $('#selectRoles').multiSelect('refresh');

}

function roles(arre) {
    var obj1 = {
        id: 1,
        nombre: 'Instructor'
    }
    var obj2 = {
        id: 2,
        nombre: 'Líder equipo técnico'
    }
    var obj3 = {
        id: 3,
        nombre: 'Líder equipo pedagógico'
    }

    arre.push(obj1)
    arre.push(obj2)
    arre.push(obj3)
    return arre
}

$('#selectRoles').change(function(){
    change = true
});