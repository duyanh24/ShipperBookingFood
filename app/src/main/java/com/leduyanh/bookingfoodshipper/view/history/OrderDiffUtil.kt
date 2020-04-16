package com.leduyanh.bookingfoodshipper.view.history

import com.leduyanh.bookingfoodshipper.base.BaseDiffUtil
import com.leduyanh.bookingfoodshipper.data.Order


class OrderDiffUtil(
    private val oldList: ArrayList<Order>,
    private val newList: ArrayList<Order>
): BaseDiffUtil<Order>(oldList,newList) {

    override fun checkItemTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun checkContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].time == newList[newItemPosition].time
                && oldList[oldItemPosition].price == newList[newItemPosition].price
                && oldList[oldItemPosition].addressCus == newList[newItemPosition].addressCus
                && oldList[oldItemPosition].addressRestau == newList[newItemPosition].addressRestau
    }
}
