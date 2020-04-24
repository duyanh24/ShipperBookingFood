package com.leduyanh.bookingfoodshipper.view.history

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.models.order.Order

class OrderViewModel:ViewModel() {

    var time = ""
    var addressRestaurant = ""
    var addressCustomer = ""
    var price = ""

    fun bindData(data: Order){
        time = data.time
        addressRestaurant = data.store.address
        addressCustomer = data.addressCus
        price = "250.000 vnđ"
    }
}