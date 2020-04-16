package com.leduyanh.bookingfoodshipper.base

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtil<T>(
    private val mOldPlace: ArrayList<T>,
    private val mNewPlace: ArrayList<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldPlace.size
    }

    override fun getNewListSize(): Int {
        return mNewPlace.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkItemTheSame(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkContentsTheSame(oldItemPosition, newItemPosition)
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    abstract fun checkItemTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean

    abstract fun checkContentsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean
}
