package com.leduyanh.bookingfoodshipper.view.orderdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.databinding.FragmentOrderDetailBinding
import kotlinx.android.synthetic.main.fragment_order_detail.*


class OrderDetailFragment : Fragment() {

    lateinit var binding: FragmentOrderDetailBinding
    lateinit var orderDetailViewModel: OrderDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_order_detail,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderDetailViewModel = OrderDetailViewModel()
        binding.viewmodel = orderDetailViewModel

        rvcOrderDetail.layoutManager = LinearLayoutManager(activity)
        orderDetailViewModel.getDataOrderDetail()

        btnOrderDetailBack.setOnClickListener {
            activity!!.onBackPressed()
        }

    }
}
