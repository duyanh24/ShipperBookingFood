package com.leduyanh.bookingfoodshipper.data.repository

import android.util.Log
import com.leduyanh.bookingfoodshipper.data.api.RetrofitClient
import com.leduyanh.bookingfoodshipper.data.models.shipper.ShipperLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login {
    private val retrofitClient = RetrofitClient.newInstance().dataApi

    fun login(email: String,  password: String, callback: ICallBack<ShipperLoginResponse>){
        val call : Call<ShipperLoginResponse> = retrofitClient.logIn(email, password)
        call.enqueue(object : Callback<ShipperLoginResponse> {
            override fun onResponse(call: Call<ShipperLoginResponse>, response: Response<ShipperLoginResponse>) {
                val data = response.body()
                if(data == null){
                    callback.getError("Không lấy được dữ liệu")
                }else {
                    callback.getData(data)
                }
            }

            override fun onFailure(call: Call<ShipperLoginResponse>, t: Throwable) {
                Log.d("ERORR:", t.toString())
                callback.getError("Lỗi kết nối!")
            }
        })
    }

}
