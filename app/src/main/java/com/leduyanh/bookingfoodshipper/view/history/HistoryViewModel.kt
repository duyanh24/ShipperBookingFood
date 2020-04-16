package com.leduyanh.bookingfoodshipper.view.history

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.data.Order

class HistoryViewModel: ViewModel() {
    var adapter = ListOrderAdapter()

    fun getDataOrder(){
        val list = ArrayList<Order>()
        list.add(Order("12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order("12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order("12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))
        list.add(Order("12:20 pm","hoàng mai hà nội","hoàng mai hà nội","25000vnd"))

        adapter.updateList(list)
    }
}