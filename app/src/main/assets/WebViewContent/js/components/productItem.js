function productItem(productName, calories) {

    return $(`
        <div class="row">
            <div class="col-6">
                <h6 class="text-center">${productName}</h6>
            </div>
            <div class="col-6">
                <h6 class="text-center">${calories}</h6>
            </div>
        </div>
        <hr>
    `);
}