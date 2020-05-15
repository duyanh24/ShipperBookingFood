package com.leduyanh.bookingfoodshipper.data.models.shipper

import com.google.gson.annotations.SerializedName

data class Shipper(@SerializedName("id") val shipperId:Int,
                   @SerializedName("name") val name:String,
                   @SerializedName("email") val email:String,
                   @SerializedName("phone") val phone:String,
                   @SerializedName("address") val address:String,
                   @SerializedName("identification") val identification:String,
                   @SerializedName("license_plates") val licensePlates:String)