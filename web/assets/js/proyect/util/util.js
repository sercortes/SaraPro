
$(function(){
    
    footer()

})

function footer(){

    let today = new Date();
    let month = today.toLocaleString('default', { month: 'long' })
    let srt = today.getDay()+' '+month+' '+today.getFullYear()
    let fecha = document.getElementById('fecha');
    fecha.innerHTML += srt 
    
}

function welcome(){

    $.notify({
        icon: "add_alert",
        message: "Bienvenido <b>Sergio Cortes</b>"
  
    },{
        type: 'info',
        timer: 2000,
        placement: {
            from: 'top',
            align: 'right'
        }
    });

}
