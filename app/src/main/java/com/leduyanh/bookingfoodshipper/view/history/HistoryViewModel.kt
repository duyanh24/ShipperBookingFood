package com.leduyanh.bookingfoodshipper.view.history

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.base.OnClickItemListener

import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.models.order.OrderDetail
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.main.HomeActivity
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailFragment
import kotlin.collections.ArrayList

class HistoryViewModel(private val orderRepository: OrderRepository): ViewModel() {

    var adapter = ListOrderAdapter()
    var dataLoadSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val listOrder = ArrayList<Order>()

    private val onClickItemListener = object : OnClickItemListener {
        override fun onClickItem(view: View, orderId: Int, position: Int) {
            val orderDetail = OrderDetail(orderId,
                listOrder[position].store.name,
                listOrder[position].store.address,
                listOrder[position].user.name,
                listOrder[position].addressCus)

            (view.context as HomeActivity).moveScreen(OrderDetailFragment(orderDetail),true)
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
}