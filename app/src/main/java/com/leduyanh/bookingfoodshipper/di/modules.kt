package com.leduyanh.bookingfoodshipper.di

import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository
import com.leduyanh.bookingfoodshipper.view.currentorder.CurrentOrderViewModel
import com.leduyanh.bookingfoodshipper.view.history.HistoryViewModel
import com.leduyanh.bookingfoodshipper.view.home.HomeViewModel
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailViewModel
import com.leduyanh.bookingfoodshipper.view.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    single { OrderRepository() }
    single { ShipperRepository() }

    viewModel { HistoryViewModel(get()) }
    viewModel { OrderDetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CurrentOrderViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}