package com.leduyanh.bookingfoodshipper.view.profile

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.shipper.Shipper
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository

class ProfileViewModel(private val shipperRepository: ShipperRepository):ViewModel(){

    val shipper:MutableLiveData<Shipper> = MutableLiveData()

    fun getDataShipper(){
        val authorization = MyApplication.token
        val idShiper = 1
        shipperRepository.getInforShipper(authorization,idShiper,object : ICallBack<Shipper>{
            override fun getData(data: Shipper) {
                shipper.value = data
            }
            override fun getError(mess: String) {
                Toast.makeText(MyApplication.instance,mess,Toast.LENGTH_LONG).show()
            }
        })
    }
}