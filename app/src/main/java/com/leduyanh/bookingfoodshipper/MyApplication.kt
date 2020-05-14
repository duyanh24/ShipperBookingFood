package com.leduyanh.bookingfoodshipper

import android.app.Application
import com.leduyanh.bookingfoodshipper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication: Application() {

    companion object {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJkdXlhbmhAZ21haWwuY29tIiwicm9sZSI6InNoaXBwZXIiLCJpYXQiOjE1ODkxNjg4MjUsImV4cCI6MTU4OTE3MjQyNX0.GVM7XJnMozu-Fc8CtBAA3QkvPY79X_pRtJ_O2n1q5yk"
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(viewModelModule)
        }
    }
}