package com.leduyanh.bookingfoodshipper.view.currentorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailFragment
import kotlinx.android.synthetic.main.fragment_direction.*

class DirectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_direction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOrderDetail.setOnClickListener {
            (context as CurrentOrderActivity).moveScreen(OrderDetailFragment(),true)
        }
    }


}
