$(document).on('click', '.btnDetails', function (e) {

    let nombre = this.value
    idVersion = this.id;
    $('#modalDetails').modal({
        backdrop: 'static',
        keyboard: false
    })
//    $('#modalTittleD').text(nombre)
    $('#modalDetails').modal('show')

    getPV(idVersion, nombre)
    getAutores(idVersion, nombre)

});

function getPV(id, nombre) {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getProductoVirtualIndividual",
        data: {
            id: id
        },
        success: function (data) {

            console.log(data)
            informePV(data, nombre)

        },
        error: function (data) {


        }
    })

}

function informePV(data, nombre) {

    $('#tittleP').html('<b>Nombre : </b>'+nombre)
    $('#description').html('<b>Descripción : </b>' + data.descripcion)
    $('#palabrasC').html('<b>Palabras Clave : </b>' + data.palabrasClave)
    $('#fechaE').html('<b>Fecha Envío : </b>' + data.versioDTO.fechaEnvio)
    $('#numeroV').html('<b># Versión : </b>' + data.versioDTO.numVersion)
    $('#instrc').html('<b>Instrucciones Instalación : </b>' + data.versioDTO.intrucionesInstalacion)
    $('#reqInstrc').html('<b>Requerimientos Instalación : </b>' + data.versioDTO.requeInstalacion)

    getDerechos(data.derechosAutor, data.versioDTO.url)
    
    
}

$(document).on('click', '.btnDown', function(){
    
    $('#modalDownload').modal({
        backdrop: 'static',
        keyboard: false
    })
    
    $('#modalDownload').modal('show')
    
})

function getDerechos(id, url){
    
     $.ajax({
        type: 'POST',
        async: true,
        url: "./getDerechoAutor",
        data: {
            id: id
        },
        success: function (data) {

        $('#tituloDerechos').html("<b>Derechos de Autor : </b>"+data.nombre)
        $('#descripcionDerechos').text(data.descripcion)
            document.getElementById('derechosAutor').innerHTML  = 
                    `<img src="${data.imagen}" class="img-fluid" style="max-height: 70px;" alt="Responsive image"></a>`
    
        document.getElementById('download').innerHTML =
            `<a class="btnDown" href="#">
             <img src="./assets/img/download.png" class="img-fluid mx-auto d-block" alt="Responsive image"></a>`
            
        $('#tituloD').text(data.nombre)
        $('#desD').text(data.descripcion)
        document.getElementById('imagenDA').innerHTML  = 
                `<img src="${data.imagen}" class="img-fluid" alt="Responsive image"></a>`
        document.getElementById('modalDownloadF').innerHTML = 
                `<a target="_blank" class="btn btn-primary float-right" href="${url}" download="">Descargar</a>`

        },
        error: function (data) {


        }
    })
            
}

function getAutores(id, nombre) {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getAutoresPro",
        data: {
            id: id
        },
        success: function (data) {

            buttonsAutor(data, nombre)

        },
        error: function (data) {


        }
    })

}

function buttonsAutor(data, nombre) {

    let stro = ``
    for (var item of data) {
        stro += `<tr>
                   <td>${item.nomFuncionario} ${item.apeFuncionario}</td>
                 </tr>`
    }
    document.getElementById('auto').innerHTML = stro

    let id = document.getElementById('webpagelink').value
    const found = data.find(element => element.idFuncionario === id)
    if (found === undefined) {
        document.getElementById('modalDF').innerHTML =
                `<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" id="${idVersion}" class="btn btn-info btnEvaluar" value="${nombre}">
                <i class="material-icons">add_task</i> Evaluar</button>`
    } else {
        document.getElementById('modalDF').innerHTML =
                `<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                 <button type="button" id="${nombre}" class="btn btn-danger btnEvaluard" value="${nombre}" disabled>
                 <i class="material-icons">add_task</i> Evaluar</button>`
    }

}