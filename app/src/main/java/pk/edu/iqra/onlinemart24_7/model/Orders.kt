package pk.edu.iqra.onlinemart24_7.model

class Orders {
        var orderNo: Int? = 0
        var Image: String? = ""
        var Title: String? = ""
        var Discount: String? = ""
        var Price: String? = ""
        var Quantity: String? = ""


        constructor():this(0,"","","","",""){

        }
        constructor(    orderNo:Int?,
                        Image: String?,
                        Title: String?,
                        Discount: String?,
                        Price: String?,
                        Quantity: String?){
            this.orderNo = orderNo
            this.Image = Image
            this.Title = Title
            this.Discount = Discount
            this.Price = Price
            this.Quantity = Quantity

        }
    }
