$(document).ready(function () {
    //  url: 'http://conisoft.org/HealthAppV2/getProductsByCategory.php',
    $('select').dropdown();
    $('.ui.dropdown').dropdown();

    let products_all = [];


    $('#addCustomProduct').on('click', function () {

        let productName = $('#selectedProduct').val();
        let calorias = $('#calorias').text().replace('g', '');
        let grasasTotales = $('#lipidos').text().replace('g', '');
        let carbs = $('#hidratos').text().replace('g', '');
        let proteinas = $('#proteina').text().replace('g', '');

        Android.addFoodItem(productName, calorias, grasasTotales, carbs, proteinas);

    })

    $('#selectedProduct').on('change', function (e){
        e.stopPropagation();
        let selected = $(this).val();

        for(let i = 0; i < products_all.length; i++){

            if(products_all[i]['ALIMENTOS'] === selected){
                $('#cantidad').text(products_all[i]['Cantidad_sugerida'] + 'g')
                $('#unidad').text(products_all[i]['Unidad'])
                $('#calorias').text(products_all[i]['Energia_Kcal'])
                $('#hidratos').text(products_all[i]['Hidratos_de_carbono_g']+ 'g')
                $('#lipidos').text(products_all[i]['Lipidos_g']+ 'g')
                $('#proteina').text(products_all[i]['Proteina_g']+ 'g')
                break;
            }
        }

    })

    $('#select').on('change', function () {
        let groupId = $(this).val()

        $.ajax({
            url: 'http://conisoft.org/HealthAppV2/getProductsByCategory.php',
            data: {groupId},
            type: 'POST',
            success: function (response) {
                try{
                    let products = JSON.parse(response)
                    $('#productsContainer').empty()
                    products.forEach(product => {

                        $('#productsContainer').append(`
                         <div class="item" data-value="${product.ALIMENTOS}">${product.ALIMENTOS}</div>
                  
                    `)
                    })

                    $('#displayContainer').removeClass('d-none');
                    $('#displayContainer').hide();
                    $('#displayContainer').show(900);

                    products_all = products;


                }catch (e){
                    console.log(e)
                }

            }
        })

    })

})