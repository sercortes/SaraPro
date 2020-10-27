$(document).on('click', '.btnDetails', function (e) {

    let nombre = this.value
    idVersion = this.id;
    $('#modalDetails').modal({
        backdrop: 'static',
        keyboard: false
    })
    $('#modalTittleD').text(nombre)
    $('#modalDetails').modal('show')

    getPV(idVersion)
    getAutores(idVersion, nombre)

});

function getPV(id) {

    $.ajax({
        type: 'POST',
        async: true,
        url: "./getProductoVirtualIndividual",
        data: {
            id: id
        },
        success: function (data) {

            console.log(data)
            informePV(data)

        },
        error: function (data) {


        }
    })

}

function informePV(data) {

    $('#description').text('Descripción : ' + data.descripcion)
    $('#palabrasC').text('Palabras Clave : ' + data.palabrasClave)
    $('#fechaE').text('Fecha Envío : ' + data.versioDTO.fechaEnvio)
    $('#numeroV').text('# Versión : ' + data.versioDTO.numVersion)
    $('#instrc').text('Instrucciones Instalación : ' + data.versioDTO.intrucionesInstalacion)
    $('#reqInstrc').text('Requerimientos Instalación : ' + data.versioDTO.requeInstalacion)

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

        $('#tituloDerechos').text('Derechos de Autor:'+data.nombre)
        $('#descripcionDerechos').text(data.descripcion)
            document.getElementById('derechosAutor').innerHTML  = 
                    `<img src="${data.imagen}" class="img-fluid text-center" alt="Responsive image"></a>`
    
        document.getElementById('download').innerHTML =
            `<a class="p-4 pl-2 btnDown" href="#">
             <img src="./assets/img/download.png" class="img-fluid" alt="Responsive image"></a>`
            
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