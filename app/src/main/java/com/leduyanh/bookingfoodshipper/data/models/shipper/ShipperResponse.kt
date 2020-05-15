package com.leduyanh.bookingfoodshipper.data.models.shipper

import com.google.gson.annotations.SerializedName

data class ShipperResponse(@SerializedName("success") val success:Boolean,
                           @SerializedName("data") val shipper: Shipper)