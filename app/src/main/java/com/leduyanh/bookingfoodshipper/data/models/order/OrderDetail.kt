package com.leduyanh.bookingfoodshipper.data.models.order

data class OrderDetail(val orderIdSelected:Int,
                       val customerName:String,
                       val customerAddress:String,
                       val retaurantName:String,
                       val retaurantAddress:String)