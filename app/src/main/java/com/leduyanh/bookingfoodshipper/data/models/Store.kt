package com.leduyanh.bookingfoodshipper.data.models

import com.google.gson.annotations.SerializedName

data class Store(@SerializedName("id") val id:Int,
                 @SerializedName("name") val name:String,
                 @SerializedName("address") val address:String)