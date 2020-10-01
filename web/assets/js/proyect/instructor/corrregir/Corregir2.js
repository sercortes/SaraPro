


function updateServlet(data, ver, verP) {
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "sendAgain?idVer=" + ver + "&&versionP=" + verP,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {

        console.log(data)
        
            if (data === '1') {
                swal('', 'Actualizado', "success").then((value) => {
                    location.reload()
                });
                ;
            } else if (data === '20'){
                swal('', 'El producto ya ha sido evaluado', "info").then((value) => {
                    location.reload()
                });
                ;
            }else if(data === '0'){
                 swal('', 'Error', "error").then((value) => {
                    location.reload()
                });
                ;   
            }

            $('#SendFile').attr('disabled', false);
            $('#cargas').removeClass('is-active');

        },
        error: function (e) {

        }
    });
}


function queryEmphy(){
    
    let form = document.getElementById('Respuestaitem')
    let str = `No hay elementos`  
    form.innerHTML = str
    
}

function tittle(data, nombreP, evaluador){
            $('#NomLista').text('Nombre: '+nombreP)
            $('#evaluador').text('Calificado por: '+evaluador)
            $('#ObservacionGeneral').text('Observación: '+data.observacion)
            $('#FechaEvaluacion').text('Fecha Evaluación: '+data.FechaEvaluacion)
}