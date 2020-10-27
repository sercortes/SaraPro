$(function(){
    
    let menu = document.getElementById('upload')
    menu.classList.add('active')
   
    autores()
    categorias()
    derechosA()
    
})

function derechosA(){
    
        $.ajax({
        type: 'POST',
        async: true,
        url:'./getDerechosAutor',
        success: function(data){

            let str = ``
            
            for(var item of data){
                str+= `<div class="form-group col-md-4">
                                    <div class="form-check">
                                        <input class="checkbox" id="r" name="derechosAutor" type="radio" value="${item.idDerecho}">
                                        <img class="img-fluid pl-3" src="${item.imagen}">
                                        <label class="form-check-label text-justify" for="defaultCheck1">
                                           ${item.nombre}:${item.descripcion}
                                        </label>
                                    </div>
                                </div>`
            }
            document.getElementById('derechosA').innerHTML = str
            
        },
        error: function(data){
            console.log('sergio')
            window.location.replace("./RedirectRol")
        }
    })
    
}

    function categorias(){
        
         $.ajax({
        type: 'POST',
        async: true,
        url:'./Categorias',
        success: function(data){

            let categoria = document.getElementById('SelectCategoria');
            let str = `<option value="">Seleccionar...</option>`
            
            for(var item of data){
                str += `<option value="${item.idCategoria}">${item.nombreCategoria}</option>`
            }
            
            categoria.innerHTML = str;
            
        },
        error: function(data){
            console.log('sergio')
            window.location.replace("./RedirectRol")
        }
    })
    
        
    }


  function autores(){
      
    $.ajax({
        type: "POST",
        url: "./Instructores",
        async: false,
        success: function (data) {
         
            let id = document.getElementById('usuario').value
            
            for (var item of data.sort()){
                if (item.idFuncionario !== id) {
                    $('#SelectAutores').append(`<option value="${item.idFuncionario}">${item.nomFuncionario} ${item.apeFuncionario}</option>`);
                }
            } 
            
        },
        error: function (e) {

            console.log(e)

        }
    });
    
    
  $('#SelectAutores').multiSelect({
        selectableHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Busca un autor...'>",
        selectionHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Busca un autor...'>",
       
        afterInit: function (ms) {
            var that = this,
                    $selectableSearch = that.$selectableUl.prev(),
                    $selectionSearch = that.$selectionUl.prev(),
                    selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                    selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

            that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                    .on('keydown', function (e) {
                        if (e.which === 40) {
                            that.$selectableUl.focus();
                            return false;
                        }
                    });

            that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                    .on('keydown', function (e) {
                        if (e.which == 40) {
                            that.$selectionUl.focus();
                            return false;
                        }
                    });
        },
        afterSelect: function (val) {
            this.qs1.cache();
            this.qs2.cache();
        },
        afterDeselect: function (val) {
            this.qs1.cache();
            this.qs2.cache();
        }
    });
    
}

 $('#SelectCategoria').change(function(){
        
        let tema = document.getElementById('SelectCategoria').value
        
        $.ajax({
            type: 'POST',
            url: "./TemasCategorias",
            async: true,
            data:{
                id:tema
            },
            success: function(data){
                console.log(data)
               
                for(var item of data){
                   $('#MultiCategoriass').multiSelect('addOption', {value: item.idTema, text: item.nombreTema, index: 0});
                }
                
            }
        })
        
        $('#MultiCategoriass').multiSelect('refresh');
        
    })
    
       $('#MultiCategoriass').multiSelect({
        selectableHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Buscar temas...'>",
        selectionHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Buscar temas...'>",
       
        afterInit: function (ms) {
            var that = this,
                    $selectableSearch = that.$selectableUl.prev(),
                    $selectionSearch = that.$selectionUl.prev(),
                    selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                    selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

            that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                    .on('keydown', function (e) {
                        if (e.which === 40) {
                            that.$selectableUl.focus();
                            return false;
                        }
                    });

            that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                    .on('keydown', function (e) {
                        if (e.which == 40) {
                            that.$selectionUl.focus();
                            return false;
                        }
                    });
        },
        afterSelect: function (val) {
            this.qs1.cache();
            this.qs2.cache();
        },
        afterDeselect: function (val) {
            this.qs1.cache();
            this.qs2.cache();
        }
    });