$(function(){
    
    let menu = document.getElementById('getList')
    menu.classList.add('active')
    
    items()
    
})

  function items(){
      
    $.ajax({
        type: "POST",
        url: "./getItems",
        async: false,
        success: function (data) {
         
            for (var item of data.sort()){
                    $('#SelectItems').append(`<option value="${item.idItem}">${item.nombre}</option>`);
            } 
            
        },
        error: function (e) {

        }
    });
    
    
  $('#SelectItems').multiSelect({
        selectableHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Busca un Item...'>",
        selectionHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Busca un Item...'>",
       
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

$(document).on('click', '#addItem', function(e){
    
    e.preventDefault()
    $(".remove").remove();
    const item = $('#nombreItem').val()
    
    if (item == '' || item == undefined) {
            swal('', 'Describa el  item!', "info")
            $('#nombreItem').focus().after("<div class='remove'><font color='red'>Escriba el item</font><div>")   
            return false
        }
        
        if (item.length >= 900) {
         swal('', 'Item muy grande!', "info")
         $('#nombreItem').val('')
         return false
    }
    
    $('#addItem').attr('disabled', true)
    $('#cargas').addClass('is-active');
    
    send(item)
    
})

function send(data){
    
        $.ajax({
            type: 'POST',
            url: './CreateItems',
            dataType: "json",
            data:{
                descripcion:data
            },
            success: function (datas) {

                $('#cargas').removeClass('is-active')
                $('#addItem').attr('disabled', false)

                if (datas!==0) {
                    swal('', 'Item creado', "success").then((value) => {
                            $('#nombreItem').val("")
                            $('#SelectItems').multiSelect('addOption', {value: datas.idLista, text: datas.descripcion, index: 0});
                             
                    });
                    ;
                } else {
                    swal('', 'Upsss', "error").then((value) => {
                        $('#nombreItem').val("")
                    });
                    ;
                }

            }, error: function (error) {

                console.log(error)

            }
        })
    
}
