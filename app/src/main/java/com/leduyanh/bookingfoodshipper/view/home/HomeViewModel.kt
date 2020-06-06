package com.leduyanh.bookingfoodshipper.view.home

import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference

class HomeViewModel(private val shipperRepository: ShipperRepository) : ViewModel(){

    fun changeStatusShipper(isOnline: Int){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        val idShiper = 1
        shipperRepository.updateStatusShipper(authorization,idShiper,isOnline,object :ICallBack<String>{
//            override fun getData(data: ArrayList<Order>) {
//            }

            override fun getError(mess: String) {
            }

            override fun getData(data: String) {
                TODO("Not yet implemented")
            }
        })
    }
}