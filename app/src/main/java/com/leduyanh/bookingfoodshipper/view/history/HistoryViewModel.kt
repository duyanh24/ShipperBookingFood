package com.leduyanh.bookingfoodshipper.view.history

import android.view.View
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.base.OnClickItemListener
import com.leduyanh.bookingfoodshipper.data.models.Store
import com.leduyanh.bookingfoodshipper.data.models.User
import com.leduyanh.bookingfoodshipper.data.models.order.Order
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
        val store = Store(1,"Bún đậu mắm tôm","Hai bà Trưng, Hà Nội")
        val user = User(1,"Lê Duy Anh")
        list.add(
            Order(
                1,
                "hoàng mai hà nội",
                "12:10 pm",
                store,
                user
            )
        )
        list.add(
            Order(
                1,
                "Ba Đình, hà nội",
                "12:10 pm",
                store,
                user
            )
        )
        list.add(
            Order(
                1,
                "thanh xuân, hà nội",
                "12:10 pm",
                store,
                user
            )
        )
        list.add(
            Order(
                1,
                "hoàng mai hà nội",
                "12:10 pm",
                store,
                user
            )
        )

        adapter.updateList(list)
    }
}