package com.leduyanh.bookingfoodshipper.data.repository

import com.leduyanh.bookingfoodshipper.data.api.RetrofitClient
import com.leduyanh.bookingfoodshipper.data.models.shipper.Shipper
import com.leduyanh.bookingfoodshipper.data.models.shipper.ShipperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShipperRepository {
    private val retrofitClient = RetrofitClient.newInstance().dataApi

    fun getInforShipper(authorization: String?,  idShiper: Int?, callback: ICallBack<Shipper>){
        val call = retrofitClient.getInfoShipper(authorization,idShiper)
        call.enqueue(object : Callback<ShipperResponse> {
            override fun onResponse(call: Call<ShipperResponse>, response: Response<ShipperResponse>) {
                val data = response.body()
                if(data == null || !data.success){
                    callback.getError("Không lấy được dữ liệu")
                }else{
                    callback.getData(data.shipper)
                }
            }
            override fun onFailure(call: Call<ShipperResponse>, t: Throwable) {
                callback.getError("Lỗi kết nối!")
            }
        })
    }

    fun updateStatusShipper(authorization: String?,  idShiper: Int,isOnline:Int, callback: ICallBack<String>){
        val call = retrofitClient.updateOnline(authorization,idShiper,isOnline)
        call.enqueue(object : Callback<Shipper>{
            override fun onFailure(call: Call<Shipper>, t: Throwable) {
                callback.getData("Thành công")
            }
            override fun onResponse(call: Call<Shipper>, response: Response<Shipper>) {
                callback.getError("Thất bại")
            }
        })
    }
}