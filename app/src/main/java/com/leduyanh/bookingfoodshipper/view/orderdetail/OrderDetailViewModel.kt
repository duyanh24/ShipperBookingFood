package com.leduyanh.bookingfoodshipper.view.orderdetail

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish

class OrderDetailViewModel: ViewModel(){
    var customerName = ""
    var customerAddress = ""
    var retaurantName = ""
    var retaurantAddress = ""
    var totalPrice = ""

    var adapter = DishAdapter()

    fun getDataOrderDetail(){
        retaurantName = "Nhà hàng Ngọc Ngân"
        retaurantAddress = "Bạch Mai, Hoàng Mai, Hà Nội"
        customerName = "Lê Duy Anh 2"
        customerAddress = "Hoàng Mai, Hà Nội"
        totalPrice = "250000 VNĐ"

        val list = ArrayList<Dish>()
        list.add(Dish("Trà sữa chân tru",10000,1))
        list.add(Dish("Trà sữa chân bò",12000,1))
        list.add(Dish("Gà rán",40000,2))
        adapter.updateList(list)
    }

}