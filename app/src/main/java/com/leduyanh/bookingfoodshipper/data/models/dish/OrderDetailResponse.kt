package com.leduyanh.bookingfoodshipper.data.models.dish

import com.google.gson.annotations.SerializedName

data class OrderDetailResponse(@SerializedName("success") val success:Boolean,
                               @SerializedName("data") val listDish: List<Dish>)