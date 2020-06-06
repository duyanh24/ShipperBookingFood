package com.leduyanh.bookingfoodshipper.data.repository

import com.leduyanh.bookingfoodshipper.data.models.order.Order

interface ICallBack<T> {
    fun getData(data: T)
    fun getError(mess:String)
}