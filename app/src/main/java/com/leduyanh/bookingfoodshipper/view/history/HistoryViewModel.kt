package com.leduyanh.bookingfoodshipper.view.history

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.base.OnClickItemListener
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.main.HomeActivity
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailFragment
import kotlin.collections.ArrayList

class HistoryViewModel(private val orderRepository: OrderRepository): ViewModel() {

    var adapter = ListOrderAdapter()
    var dataLoadSuccess: MutableLiveData<Boolean> = MutableLiveData()

    private var orderIdSelected = -1
    //var customerName = ""
    var customerAddress = ""
    var retaurantName = ""
    var retaurantAddress = ""
    var totalPrice :MutableLiveData<Int> = MutableLiveData()
    val listOrder = ArrayList<Order>()

    private val onClickItemListener = object : OnClickItemListener {
        override fun onClickItem(view: View, orderId: Int, position: Int) {
            orderIdSelected = orderId
//            retaurantName = listOrder[position].store.name
//            retaurantAddress = listOrder[position].store.address
//            customerName = listOrder[position].user.name
//            customerAddress = listOrder[position].addressCus

            (view.context as HomeActivity).moveScreen(OrderDetailFragment(),true)
        }
    }

    init {
        adapter.onClickItemListener = onClickItemListener
    }

    fun getDataOrder(){

        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        val idShiper = sharedPreference?.getInt(SaveSharedPreference.ID)

        orderRepository.getDataOrder(authorization,idShiper, object : ICallBack<List<Order>>{
            override fun getData(data: List<Order>) {
                listOrder.clear()
                listOrder.addAll(data)
                adapter.updateList(data as ArrayList<Order>)
                dataLoadSuccess.value = true
            }

            override fun getError(mess: String) {
                dataLoadSuccess.value = false
            }
        })
    }

    //var dishAdapter = DishAdapter()

    fun getDataOrderDetail(){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)

        var sumPrice = 0
        orderRepository.getDataOrderDetail(authorization,orderIdSelected, object : ICallBack<List<Dish>>{
            override fun getData(data: List<Dish>) {

//                dishAdapter.updateList(data as ArrayList<Dish>)
//                for (element in data){
//                    sumPrice+= element.price
//                }
            }

            override fun getError(mess: String) {
            }
        })
        totalPrice.value = sumPrice
    }
}