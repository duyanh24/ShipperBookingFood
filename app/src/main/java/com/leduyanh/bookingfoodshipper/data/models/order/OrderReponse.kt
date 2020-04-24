package com.leduyanh.bookingfoodshipper.data.models.order

data class OrderReponse(val success:Boolean,
                        val listOrder: ArrayList<Order>)