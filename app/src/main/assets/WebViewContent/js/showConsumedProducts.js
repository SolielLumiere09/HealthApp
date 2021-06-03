$(document).ready(function () {
    products = JSON.parse(Android.getFoodItems())

    products.forEach(product => {
        let node =  productItem(product['product_name'], product['calories']);

        node.find('button').on('click', function () {
            let productName = node.find('.productName').text().toString();
            $(this).parent().parent().remove();
            Android.removeItem(productName)

        })

        $('#productsContainer').append(node)
    })

})