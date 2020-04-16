package com.leduyanh.bookingfoodshipper.view.currentorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.utils.addFragment

class CurrentOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_order)

        supportFragmentManager.addFragment(R.id.currentOrderContainer,DirectionFragment(),false)
    }

    fun moveScreen(fragment: Fragment, isBackStack: Boolean){
        supportFragmentManager.addFragment(R.id.currentOrderContainer,fragment,isBackStack)
    }
}
