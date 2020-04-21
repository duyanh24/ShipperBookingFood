package com.leduyanh.bookingfoodshipper.view.orderdetail

import com.leduyanh.bookingfoodshipper.base.BaseDiffUtil
import com.leduyanh.bookingfoodshipper.data.dish.Dish

class DishDiffUtil(
    private val oldList: ArrayList<Dish>,
    private val newList: ArrayList<Dish>
): BaseDiffUtil<Dish>(oldList,newList) {

    override fun checkItemTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun checkContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].price == newList[newItemPosition].price
                && oldList[oldItemPosition].quantity == newList[newItemPosition].quantity
    }
}