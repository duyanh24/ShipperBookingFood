package com.leduyanh.bookingfoodshipper.di

import com.leduyanh.bookingfoodshipper.view.history.HistoryViewModel
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { HistoryViewModel() }
    viewModel { OrderDetailViewModel() }
}