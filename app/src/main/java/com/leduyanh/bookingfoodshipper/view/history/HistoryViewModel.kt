package com.leduyanh.bookingfoodshipper.view.history

import android.view.View
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.base.OnClickItemListener
import com.leduyanh.bookingfoodshipper.data.Order
import com.leduyanh.bookingfoodshipper.view.main.HomeActivity
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailFragment

class HistoryViewModel: ViewModel() {

    var adapter = ListOrderAdapter()

    private val onClickItemListener = object : OnClickItemListener {
        override fun onClickItem(view: View, orderId: Int) {
            (view.context as HomeActivity).moveScreen(OrderDetailFragment(),true)
        }
    }

    init {
        adapter.onClickItemListener = onClickItemListener
    }

    fun getDataOrder(){
        val list = ArrayList<Order>()
        list.add(Order(1,"12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order(2,"12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order(3,"12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order(4,"12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))

        adapter.updateList(list)
    }
}