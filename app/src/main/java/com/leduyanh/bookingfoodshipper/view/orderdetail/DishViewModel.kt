package com.leduyanh.bookingfoodshipper.view.orderdetail

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish

class DishViewModel: ViewModel() {
    var name = ""
    var quantity = ""
    var price = ""

    fun bindData(data: Dish){
        name = data.dish.name
        price = data.dish.price.toString()+" vnđ"
        quantity = "x"+ data.quantity.toString()
    }
}