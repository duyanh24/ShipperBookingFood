package com.leduyanh.bookingfoodshipper.data.models.dish

import com.google.gson.annotations.SerializedName

data class Dish(@SerializedName("name") val name: String,
                @SerializedName("price") val price:Int,
                @SerializedName("quantity") val quantity: Int,
                @SerializedName("dish") val dish: Dish)