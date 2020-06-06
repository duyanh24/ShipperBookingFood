package com.leduyanh.bookingfoodshipper.view.profile

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.shipper.Shipper
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference

class ProfileViewModel(private val shipperRepository: ShipperRepository): ViewModel(){

    val shipper:MutableLiveData<Shipper> = MutableLiveData()


    fun getDataShipper(){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        val idShiper = sharedPreference?.getInt(SaveSharedPreference.ID)

        shipperRepository.getInforShipper(authorization,idShiper, object : ICallBack<Shipper>{
            override fun getData(data: Shipper) {
                shipper.value = data
            }
            override fun getError(mess: String) {
                Toast.makeText(MyApplication.instance,mess,Toast.LENGTH_LONG).show()
            }
        })
    }
}