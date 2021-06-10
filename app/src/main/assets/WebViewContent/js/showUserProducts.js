$(document).ready(function () {
    let id = Android.getUserId();
    let productsContainer = $('#productsContainer');
    let regexExp = new RegExp('[0-9]+')

    let productsArray = {}

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
                        let totalFat = response.product.nutriments['fat_serving'];
                        let carbs = response.product.nutriments['carbohydrates_serving'];
                        let protein = response.product.nutriments['proteins_serving'];
                        let sodium = response.product.nutriments['sodium_serving'];
                        let sugar = response.product.nutriments['sugars_serving'];
                        let serving_size = regexExp.exec(response.product['serving_size']).pop();


                        let node = showOwnProductItem(productName, calories);


                        //name, Float.parseFloat(calories), Float.parseFloat(totalFat), Float.parseFloat(carbs), Float.parseFloat(protein), Float.parseFloat(sodium), Float.parseFloat(sugar)
                        //'kit ka' : {'cdcdc' : 132, 'cdcd' : 12}

                        productsArray[`${productName}`] = {
                            calories : `${calories}`,
                            totalFat : `${totalFat}`,
                            carbs : `${carbs}`,
                            protein : `${protein}`,
                            sodium : `${sodium}`,
                            sugar : `${sugar}`,
                            serving_size : `${serving_size}`
                        }


                        node.find('button').on('click', function (e) {
                            let productName = node.find('.productName').text()
                            let product = productsArray[productName]


                            Android.showFoodDetails(productName,product.calories, product.totalFat, product.carbs, product.protein, product.sodium, product.sugar, product.serving_size)
                        })

                        productsContainer.append(node);
                    }

                })

            })



            console.log(products)
        }
    })

})