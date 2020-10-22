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

    document.getElementById('download').innerHTML =
            `<a target="_blank" class="float-right" href="${data.versioDTO.url}" download="">
             <img src="./assets/img/download.png" class="img-fluid" alt="Responsive image"></a>`

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