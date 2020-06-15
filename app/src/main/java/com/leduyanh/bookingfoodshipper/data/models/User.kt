package com.leduyanh.bookingfoodshipper.data.models

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") val userId:Int,
                @SerializedName("name") val name:String,
                @SerializedName("phone") val phone:String)