$(document).ready(function () {
    products = JSON.parse(Android.getFoodItems())

    products.forEach(product => {
        $('#productsContainer').append(
            productItem(product['product_name'], product['calories'])
        )
    })

})