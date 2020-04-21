package com.leduyanh.bookingfoodshipper.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.leduyanh.bookingfoodshipper.R

internal fun FragmentManager.addFragment(idContainer: Int, fragment: Fragment, isBackStack: Boolean){
    val transaction = this.beginTransaction()

    if(isBackStack){
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        transaction.add(idContainer,fragment)
        transaction.addToBackStack(null)
    }else{
        transaction.replace(idContainer,fragment)
        transaction.disallowAddToBackStack()
    }
    transaction.commit()
}