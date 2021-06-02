$(document).ready(function () {
    let id = Android.getUserId();
    let productsContainer = $('#productsContainer');
    $.ajax({
        url: 'http://conisoft.org/HealthAppV2/getProducts.php',
        type: 'POST',
        async: false,
        data: {id},
        success: function (response) {
            let products = JSON.parse(response)

            products.forEach(product => {
                let productName = product.Nombre; //guardar el nombre
                let url = product.Barcode;

                $.ajax({
                    url: url,
                    success: function (response) {
                        let calories = response.product.nutriments["energy-kcal_serving"];

                        productsContainer.append(addProductItem(productName, calories))

                        Android.addFoodItem(
                            productName,
                            response.product.nutriments['energy-kcal_serving'],
                            response.product.nutriments['fat_serving'],
                            response.product.nutriments['carbohydrates_serving'],
                            response.product.nutriments['proteins_serving']
                            )

                    }

                })

            })

        }
    })
})