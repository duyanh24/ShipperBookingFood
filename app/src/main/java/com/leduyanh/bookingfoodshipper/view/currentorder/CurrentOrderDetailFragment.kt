package com.leduyanh.bookingfoodshipper.view.currentorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.leduyanh.bookingfoodshipper.R

import com.leduyanh.bookingfoodshipper.databinding.FragmentCurrentOrderDetailBinding
import kotlinx.android.synthetic.main.fragment_current_order_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentOrderDetailFragment : Fragment() {

    private lateinit var binding: FragmentCurrentOrderDetailBinding
    private val currentOrderViewModel: CurrentOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_current_order_detail,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = currentOrderViewModel

        rvcOrderCurrentDetail.layoutManager = LinearLayoutManager(activity)
        currentOrderViewModel.getDataOrderDetail()

        btnOrderCurentDetailBack.setOnClickListener {
            activity!!.onBackPressed()
        }
    }
}
