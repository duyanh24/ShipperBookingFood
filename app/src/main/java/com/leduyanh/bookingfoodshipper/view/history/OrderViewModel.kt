package com.leduyanh.bookingfoodshipper.view.history

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.Order

class OrderViewModel:ViewModel() {

    var time = ""
    var addressRestaurant = ""
    var addressCustomer = ""
    var price = ""

    fun bindData(data: Order){
        time = data.time
        addressRestaurant = data.addressRestau
        addressCustomer = data.addressCus
        price = data.price
    }
}