package com.leduyanh.bookingfoodshipper.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    lateinit var dataApi: DataService
    lateinit var instance: RetrofitClient

    companion object {
        private const val URL = "http://192.168.43.200:5000/api/"
        fun newInstance() = RetrofitClient().apply {
            val retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                    URL
                ).build()
            dataApi = retrofit.create(DataService::class.java)
            instance = this
        }
    }
}
