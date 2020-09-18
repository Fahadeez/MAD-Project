package pk.edu.iqra.onlinemart24_7.model

import android.graphics.Bitmap

class Product {
    var Image: String? = null
    var Title: String? = null
    var Discount: String? = null
    var Price: String? = null
    var Quantity: String? = null


    constructor():this("","","","",""){

    }
    constructor(    Image: String?,
                    Title: String?,
                    Discount: String?,
                    Price: String?,
                    Quantity: String?){
        this.Image = Image
        this.Title = Title
        this.Discount = Discount
        this.Price = Price
        this.Quantity = Quantity

    }
}