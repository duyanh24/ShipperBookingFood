package com.leduyanh.bookingfoodshipper.data.models.order

import com.google.gson.annotations.SerializedName

data class OrderReponse(@SerializedName("success") val success:Boolean,
                        @SerializedName("data") val listOrder: ArrayList<Order>)