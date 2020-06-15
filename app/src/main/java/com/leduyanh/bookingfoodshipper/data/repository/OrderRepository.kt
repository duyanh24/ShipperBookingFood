package com.leduyanh.bookingfoodshipper.data.repository

import com.leduyanh.bookingfoodshipper.data.api.RetrofitClient
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.models.dish.OrderDetailResponse
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.models.order.OrderCurrentReponse
import com.leduyanh.bookingfoodshipper.data.models.order.OrderReponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class OrderRepository {
    private val retrofitClient = RetrofitClient.newInstance().dataApi

    fun getDataOrder(authorization: String?,  idShiper: Int?, callback: ICallBack<List<Order>>){
        val call = retrofitClient.getOrderShipper(authorization,idShiper)
        call.enqueue(object : Callback<OrderReponse> {
            override fun onResponse(call: Call<OrderReponse>, response: Response<OrderReponse>) {
                val data = response.body()
                if(data == null || !data.success){
                    callback.getError("Không lấy được dữ liệu")
                }else{
                    callback.getData(data.listOrder)
                }
            }
            override fun onFailure(call: Call<OrderReponse>, t: Throwable) {
                callback.getError("Lỗi kết nối!")
            }
        })
    }

    fun getCurrentOrder(authorization: String?, callback: ICallBack<Order>){
        val call = retrofitClient.getCurrentOrder(authorization)
        call.enqueue(object : Callback<OrderCurrentReponse> {
            override fun onResponse(call: Call<OrderCurrentReponse>, response: Response<OrderCurrentReponse>) {
                val data = response.body()
                if(data == null || !data.success){
                    callback.getError("Không lấy được dữ liệu")
                }else{
                    callback.getData(data.order)
                }
            }
            override fun onFailure(call: Call<OrderCurrentReponse>, t: Throwable) {
                callback.getError("Lỗi kết nối!")
            }
        })
    }

    fun getDataOrderDetail(authorization: String?, orderId: Int, callback: ICallBack<List<Dish>>){
        val call = retrofitClient.getOrderById(authorization,orderId)
        call.enqueue(object : Callback<OrderDetailResponse> {
            override fun onResponse(call: Call<OrderDetailResponse>, response: Response<OrderDetailResponse>) {
                val data = response.body()
                if(data == null || !data.success){
                    callback.getError("Không lấy được dữ liệu")
                }else{
                    callback.getData(data.listDish)
                }
            }
            override fun onFailure(call: Call<OrderDetailResponse>, t: Throwable) {
                callback.getError("Lỗi kết nối!")
            }
        })
    }

    fun updateStatusOrder(authorization: String?,  idShiper: Int,statusOrder:Int, callback: ICallBack<String>){
        val call = retrofitClient.updateStatusOrder(authorization,idShiper, statusOrder)
        call.enqueue(object : Callback<Order>{
            override fun onFailure(call: Call<Order>, t: Throwable) {
                callback.getData("Thành công")
            }
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                callback.getError("Thất bại")
            }
        })
    }
}