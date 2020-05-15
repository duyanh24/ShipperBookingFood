package com.leduyanh.bookingfoodshipper.data.models.order

import com.google.gson.annotations.SerializedName
import com.leduyanh.bookingfoodshipper.data.models.Store
import com.leduyanh.bookingfoodshipper.data.models.User

data class Order(@SerializedName("id") val orderId:Int,
                 @SerializedName("address") val addressCus:String,
                 @SerializedName("time") val time:String,
                 @SerializedName("status") val status:Int,
                 @SerializedName("store") val store: Store,
                 @SerializedName("user") val user: User,
                 @SerializedName("totalPrice") val totalPrice: Int
)