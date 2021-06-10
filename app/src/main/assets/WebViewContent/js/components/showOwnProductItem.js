const showOwnProductItem = (productName, calories) => {

    return $(`
         <div class="row">
            <div class="col-5 mt-2">
                <h6 class="text-center productName">${productName}</h6>
            </div>
            <div class="col-4 mt-2">
                <h6 class="text-center">${calories}</h6>
            </div>
             <div class="col-3 text-center mb-3">
               <button class="ui icon button">
                    <i class="search icon"></i>
                </button>
            </div>
            <hr>
        </div>
    `);
}