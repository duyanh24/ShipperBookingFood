package com.leduyanh.bookingfoodshipper.data.models.shipper

import com.google.gson.annotations.SerializedName

class ShipperLoginResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("token") val token: String,
    @SerializedName("data") val shipper: Shipper
)