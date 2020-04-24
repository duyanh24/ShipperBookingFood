package com.leduyanh.bookingfoodshipper.data.models.shipper

data class Shipper(val shipperId:Int,
                   val name:String,
                   val email:String,
                   val phone:String,
                   val address:String,
                   val identification:String,
                   val licensePlates:String)