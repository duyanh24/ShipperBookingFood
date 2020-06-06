package com.leduyanh.bookingfoodshipper.view.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_waiting.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_waiting)

        val animRun : Animation = AnimationUtils.loadAnimation(this, R.anim.run_from_left_to_right)
//
        waitingLogo.startAnimation(animRun)


        Handler().postDelayed({
            val sharePreference : SaveSharedPreference = SaveSharedPreference(this@MainActivity)

            val isLogin = sharePreference.getBoolean(SaveSharedPreference.IS_LOGIN)

            if (isLogin == true) {
                val intent : Intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent : Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }, 2000)


    }
}