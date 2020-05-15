package com.leduyanh.bookingfoodshipper.data.models.dish

import com.google.gson.annotations.SerializedName

data class Dish(@SerializedName("name_dish") val name: String,
                @SerializedName("current_price") val price:Int,
                @SerializedName("quantity") val quantity: Int)