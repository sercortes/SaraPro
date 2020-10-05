var arrayNotify = []

$(function () {

    barraNotification()

})

function barraNotification() {

    $.ajax({
        type: 'POST',
        async: false,
        url: "./getNotificationsGeneralBarra",
        success: function (data) {

            if (data.length <= 0) {
                queryEmphy()
                return false
            }
            
            generateListNav(data)
            
        }

    })

}

$('#campana').on('hidden.bs.dropdown', function () {
    // do something…

    if (arrayNotify.length <= 0) {
        return false;
    }

    $.ajax({
        type: 'POST',
        async: true,
        data: arrayNotify,
        url: "./updateNotificationsInstructor?notificaciones=" + arrayNotify,
        success: function (data) {

            if (data) {
                barraNotification()
            } else {
                console.log('error')
                barraNotification()
            }

        }

    })

})

function queryEmphy(){
    document.getElementById('numberNotifications').innerHTML = 0
    document.getElementById('barraN').innerHTML = '<a class="dropdown-item" href="#">No hay notificaciones</a>'
}

function generateListNav(data) {

    document.getElementById('numberNotifications').innerHTML = data.length
    let barra = document.getElementById('barraN')

    let str = ''
    for (var item of data) {
        str += `  <a class="dropdown-item" href="./Notify">${item.descripcionNotificacion}</a>`
        arrayNotify.push(item.idDetalleNotificacion)
    }
    barra.innerHTML = str
    
}



