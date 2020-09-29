/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {

    $('#exampleModal').modal({backdrop: 'static', keyboard: false})

    $('#exampleModal').modal('show')

    document.body.style.backgroundImage = "url('assets/img/bg1.png')";
    let id = document.getElementById('idUser').value

    let roles = getRolesTwo(id)

    let modalfot = document.getElementById('buttons')

    let str = ``
    let color = ''

    for (var item of roles) {
        if (item.idRol == 1) {
            color = 'primary'
        }
        if (item.idRol == 2) {
            color = 'info'
        }
        if (item.idRol == 3) {
            color = 'warning'
        }
         if (item.idRol == 4) {
            color = 'success'
        }
        
        str += `<button type="button" class="btns${item.idRol} btn btn-${color}" value="${item.idRol}">${item.nombreRol}</button>`
    }

    modalfot.innerHTML = str


})

$(document).on('click', '.btns1', function () {
    setMyRol(1)
})

$(document).on('click', '.btns2', function () {
    setMyRol(2)

})

$(document).on('click', '.btns3', function () {
    setMyRol(3)
})

$(document).on('click', '.btns4', function () {
    setMyRol(4)
})


function setMyRol(rol) {

    $.ajax({
        url: "./setMyRol",
        type: 'POST',
        async: false,
        data: {
            idRol: rol
        },
        success: function (data) {

            window.location.replace("./RedirectRol")

        }, error: function (data) {


        }
    })

}

function getRolesTwo(idFuncionario) {

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