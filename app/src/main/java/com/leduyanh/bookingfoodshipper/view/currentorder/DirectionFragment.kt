package com.leduyanh.bookingfoodshipper.view.currentorder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.databinding.FragmentDirectionBinding
import com.leduyanh.bookingfoodshipper.view.orderdetail.OrderDetailFragment
import kotlinx.android.synthetic.main.fragment_direction.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DirectionFragment : Fragment() {

    private lateinit var binding: FragmentDirectionBinding
    private val currentOrderViewModel:CurrentOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_direction,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = currentOrderViewModel
        currentOrderViewModel.getDataOrder()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnOrderDetail.setOnClickListener {
            (context as CurrentOrderActivity).moveScreen(CurrentOrderDetailFragment(),true)
        }

        btnCall.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                val uri = Uri.parse("tel:" + "2313132154")
                val i = Intent(Intent.ACTION_CALL, uri)
                startActivity(i)
            }
        }

        btnSendMessage.setOnClickListener {
            val number = "12346556" // The number on which you want to send SMS

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.fromParts("sms", number, null)
                )
            )
        }
    }


}
