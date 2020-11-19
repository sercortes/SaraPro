var arrayNotify = []

$(function () {

    setTimeout(() => {
        barraNotification()
    },
        10000
    );

//    setInterval(() => {
//        barraNotification()
//    }, 10000)

})

function barraNotification() {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getSizeNotification",
        success: function (data) {

            console.log(data)

            if (data.length <= 0) {
                queryEmphy()
                return false
            }

            generateListNav(data)

        }

    })

}


function queryEmphy() {
    document.getElementById('numberNotifications').innerHTML = 0
    document.getElementById('barraN').innerHTML = '<a class="dropdown-item" href="#">No hay notificaciones</a>'
}

function generateListNav(data) {

    document.getElementById('numberNotifications').innerHTML = data
    let barra = document.getElementById('barraN')

    let str = `<a class="dropdown-item" href="./ProductsCoor">Hay ${data} producto por evaluar</a>`

    barra.innerHTML = str

}



