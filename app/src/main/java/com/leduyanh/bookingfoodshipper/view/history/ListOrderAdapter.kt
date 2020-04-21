package com.leduyanh.bookingfoodshipper.view.history

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.base.BaseAdapter
import com.leduyanh.bookingfoodshipper.base.OnClickItemListener
import com.leduyanh.bookingfoodshipper.data.Order

class ListOrderAdapter(private var listOrder: ArrayList<Order> = ArrayList())
    : BaseAdapter<Order>(listOrder) {

    var onClickItemListener: OnClickItemListener? = null

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

    override fun onClickItem(v: View, adapterPosition: Int) {
        onClickItemListener?.onClickItem(v,listOrder[adapterPosition].orderId)
    }
}