function addProductItem(productName, calories) {

    let node =  $(`
        <div class="row">
            <div class="col-5 mt-2">
                <h6 class="text-center">${productName}</h6>
            </div>
            <div class="col-4 mt-2">
                <h6 class="text-center">${calories}</h6>
            </div>
             <div class="col-3">
                <button class="ui icon button positive btnAddFood">
                    +
                </button>
            </div>
        </div>
        <hr>
    `);

    node.find('.btnAddFood').on('click', function (e) {
        let productName = $(this).parent().parent().children().first().children().text()
        Android.addFood(productName);
    })


    return node;
}