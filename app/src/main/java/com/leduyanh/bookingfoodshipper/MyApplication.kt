package com.leduyanh.bookingfoodshipper

import android.app.Application
import com.leduyanh.bookingfoodshipper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication: Application() {

    companion object {
        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJkdXlhbmhAZ21haWwuY29tIiwicm9sZSI6InNoaXBwZXIiLCJpYXQiOjE1OTE0MTUwNzIsImV4cCI6MTU5MTQxODY3Mn0.TYdiKiib3nTPGwqR9aa118v5rv1fbHC8E85pHL8SWhI"
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