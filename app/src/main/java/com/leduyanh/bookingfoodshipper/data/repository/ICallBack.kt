package com.leduyanh.bookingfoodshipper.data.repository

interface ICallBack<T> {
    fun getData(data: T)
    fun getError(mess:String)
}