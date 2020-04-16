package com.leduyanh.bookingfoodshipper.view.neworder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.view.currentorder.CurrentOrderActivity
import kotlinx.android.synthetic.main.activity_new_order.*

class NewOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        btnReceiveOrder.setOnClickListener {
            val intent = Intent(this,CurrentOrderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
