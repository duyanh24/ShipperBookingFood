package com.leduyanh.bookingfoodshipper.view.history

import com.leduyanh.bookingfoodshipper.base.BaseDiffUtil
import com.leduyanh.bookingfoodshipper.data.models.order.Order


class OrderDiffUtil(
    private val oldList: ArrayList<Order>,
    private val newList: ArrayList<Order>
): BaseDiffUtil<Order>(oldList,newList) {

    override fun checkItemTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun checkContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].time == newList[newItemPosition].time
                && oldList[oldItemPosition].status == newList[newItemPosition].status
                && oldList[oldItemPosition].addressCus == newList[newItemPosition].addressCus
                && oldList[oldItemPosition].orderId == newList[newItemPosition].orderId
    }
}
