package com.leduyanh.bookingfoodshipper.data.models.order

import com.google.gson.annotations.SerializedName

data class OrderCurrentReponse(@SerializedName("success") val success:Boolean,
                          @SerializedName("data") val order: Order)