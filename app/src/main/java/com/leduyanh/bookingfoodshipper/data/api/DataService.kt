package com.leduyanh.bookingfoodshipper.data.api

import com.leduyanh.bookingfoodshipper.data.models.dish.OrderDetailResponse
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.models.order.OrderCurrentReponse
import com.leduyanh.bookingfoodshipper.data.models.order.OrderReponse
import com.leduyanh.bookingfoodshipper.data.models.shipper.Shipper
import com.leduyanh.bookingfoodshipper.data.models.shipper.ShipperLoginResponse
import com.leduyanh.bookingfoodshipper.data.models.shipper.ShipperResponse
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @POST("shipper/login")
    @FormUrlEncoded
    fun logIn(@Field("email") email: String?, @Field("password") password: String?): Call<ShipperLoginResponse>

    @GET("shipper/{id}")
    fun getInfoShipper(
        @Header("Authorization") authorization: String?, @Path("id") id: Int?
    ): Call<ShipperResponse>

    @GET("order/shipper/{id}")
    fun getOrderShipper(
        @Header("Authorization") authorization: String?, @Path("id") id: Int?
    ): Call<OrderReponse>

    @GET("orderdetail/{id}")
    fun getOrderById(
        @Header("Authorization") authorization: String?, @Path("id") id: Int
    ): Call<OrderDetailResponse>

    @GET("neworder/shipper")
    fun getCurrentOrder(@Header("Authorization") authorization: String?): Call<OrderCurrentReponse>

    @PUT("shipper/{id}")
    @FormUrlEncoded
    fun updateOnline(
        @Header("Authorization") authorization: String?, @Path("id") id: Int, @Field("isOnline") isOnline: Int
    ): Call<Shipper>

    @PUT("order/{id}")
    @FormUrlEncoded
    fun updateStatusOrder(
        @Header("Authorization") authorization: String?, @Path(
            "id"
        ) id: Int, @Field("status") status: Int
    ): Call<Order>
}