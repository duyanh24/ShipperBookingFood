package com.leduyanh.bookingfoodshipper.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

internal fun FragmentManager.addFragment(idContainer: Int, fragment: Fragment, isBackStack: Boolean){
    val transaction = this.beginTransaction()

    if(isBackStack){
        transaction.add(idContainer,fragment)
        transaction.addToBackStack(null)
    }else{
        transaction.replace(idContainer,fragment)
        transaction.disallowAddToBackStack()
    }
    transaction.commit()
}