package com.leduyanh.bookingfoodshipper.view.history

import androidx.recyclerview.widget.DiffUtil
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.base.BaseAdapter
import com.leduyanh.bookingfoodshipper.data.Order

class ListOrderAdapter(private var listOrder: ArrayList<Order> = ArrayList())
    : BaseAdapter<Order>(listOrder) {
    override fun getValueItem(adapterPosition: Int): Order {
        return listOrder[adapterPosition]
    }

    override fun getItemLayout(): Int {
        return R.layout.order_item
    }

    override fun updateList(newList: ArrayList<Order>) {
        val diffResult = DiffUtil.calculateDiff(
            OrderDiffUtil(
                listOrder,
                newList
            )
        )
        listOrder.clear()
        listOrder.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}