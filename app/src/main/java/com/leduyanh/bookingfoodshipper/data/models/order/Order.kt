package com.leduyanh.bookingfoodshipper.data.models.order

import com.leduyanh.bookingfoodshipper.data.models.Store
import com.leduyanh.bookingfoodshipper.data.models.User

data class Order(val orderId:Int,
                 val addressCus:String,
                 val time:String,
                 val store: Store,
                 val user: User
)