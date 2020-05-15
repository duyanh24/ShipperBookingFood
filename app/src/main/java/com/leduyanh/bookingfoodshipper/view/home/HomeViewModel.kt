package com.leduyanh.bookingfoodshipper.view.home

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository

class HomeViewModel(private val shipperRepository: ShipperRepository) : ViewModel(){

    fun changeStatusShipper(isOnline: Int){
        val authorization = MyApplication.token
        val idShiper = 1
        shipperRepository.updateStatusShipper(authorization,idShiper,isOnline,object :ICallBack<String>{
            override fun getData(data: String) {
            }
            override fun getError(mess: String) {
            }
        })
    }
}