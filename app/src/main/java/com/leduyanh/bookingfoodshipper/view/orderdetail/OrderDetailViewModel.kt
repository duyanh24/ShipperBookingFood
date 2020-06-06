package com.leduyanh.bookingfoodshipper.view.orderdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.models.order.OrderDetail
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference

class OrderDetailViewModel(private val orderRepository: OrderRepository): ViewModel(){
    var customerName = ""
    var customerAddress = ""
    var retaurantName = ""
    var retaurantAddress = ""
    var totalPrice:MutableLiveData<String> = MutableLiveData()

    var adapter = DishAdapter()

    fun getDataOrderDetail(orderSelected: OrderDetail){
        retaurantName = orderSelected.retaurantName
        retaurantAddress = orderSelected.retaurantAddress
        customerName = orderSelected.customerName
        customerAddress = orderSelected.customerAddress


        val orderId = orderSelected.orderIdSelected
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)

        var sumPrice = 0
        orderRepository.getDataOrderDetail(authorization,orderId,object : ICallBack<List<Dish>>{
            override fun getData(data: List<Dish>) {
                adapter.updateList(data as ArrayList<Dish>)
                for (element in data){
                    sumPrice+= element.price
                    totalPrice.value = "$sumPrice VNĐ"
                }
            }
            override fun getError(mess: String) {
            }
        })
    }
}