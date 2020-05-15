package com.leduyanh.bookingfoodshipper.view.orderdetail

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository

class OrderDetailViewModel(private val orderRepository: OrderRepository): ViewModel(){
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

        val authorization = MyApplication.token
        val orderId = 16

        var sumPrice = 0
        orderRepository.getDataOrderDetail(authorization,orderId,object : ICallBack<List<Dish>>{
            override fun getData(data: List<Dish>) {
                adapter.updateList(data as ArrayList<Dish>)
                for (i in 0..data.size-1){
                    sumPrice+= data[i].price
                }
            }
            override fun getError(mess: String) {
            }
        })
        totalPrice = sumPrice.toString() + " VNĐ"
    }
}