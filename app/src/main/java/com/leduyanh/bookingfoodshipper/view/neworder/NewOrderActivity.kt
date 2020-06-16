package com.leduyanh.bookingfoodshipper.view.neworder

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.databinding.ActivityNewOrderBinding
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.currentorder.CurrentOrderActivity
import com.leduyanh.bookingfoodshipper.view.currentorder.CurrentOrderViewModel
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.android.synthetic.main.activity_new_order.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewOrderActivity : AppCompatActivity() {

   // private lateinit var mSocket: Socket
    private val currentOrderViewModel: CurrentOrderViewModel by viewModel()
    private lateinit var binding: ActivityNewOrderBinding
    lateinit var sharePreference  : SaveSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_order)
        binding.lifecycleOwner = this
        binding.viewmodel = currentOrderViewModel
        currentOrderViewModel.getDataOrder(0)

//        mSocket = IO.socket(MyApplication.URL+"/")
//        mSocket.connect()

        sharePreference  = SaveSharedPreference(this)
        val idNewOrder = sharePreference.getInt(SaveSharedPreference.ID_NEW_ORDER)
        val idShipper = sharePreference.getInt(SaveSharedPreference.ID)

        val mediaNotification = MediaPlayer.create(this,R.raw.notification)
        mediaNotification.start()

        val countDown = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtCountDown.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val jsonString = "{\"shipper_id\": $idShipper,\"order_id\":$idNewOrder}"
                //val jsonString = "{\"shipper_id\": 8,\"order_id\":$idNewOrder}"
                MyApplication.mSocket.emit("shipper-cancel-order",jsonString)
                onBackPressed()
            }
        }.start()

        btnReceiveOrder.setOnClickListener {
            currentOrderViewModel.changeStatusShipper(2)
            currentOrderViewModel.changeStatusOrder(1)
            sharePreference.putBoolean(SaveSharedPreference.DELIVERY.first,true)
            countDown.cancel()
            MyApplication.mSocket.emit("shipper-receive-order",idNewOrder)
            val intent = Intent(this,CurrentOrderActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCancel.setOnClickListener {
            val jsonString = "{\"shipper_id\": $idShipper,\"order_id\":$idNewOrder}"
            MyApplication.mSocket.emit("shipper-cancel-order",jsonString)
            sharePreference.putInt(SaveSharedPreference.ID_NEW_ORDER.first, -1)
            countDown.cancel()
            onBackPressed()
        }
    }
}
