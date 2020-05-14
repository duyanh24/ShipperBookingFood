package com.leduyanh.bookingfoodshipper.view.history

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.models.order.Order

class OrderViewModel:ViewModel() {

    var time = ""
    var addressRestaurant = ""
    var addressCustomer = ""
    var status = ""

    fun bindData(data: Order){
        time = data.time
        addressRestaurant = data.store.name
        addressCustomer = data.addressCus
        when(data.status){
            1 -> status = "Đang giao hàng"
            2 -> status = "Đã hoàn thành"
            3 -> status = "Hủy đơn"
        }
    }
}