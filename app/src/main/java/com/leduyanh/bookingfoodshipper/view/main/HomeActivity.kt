package com.leduyanh.bookingfoodshipper.view.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.utils.addFragment
import com.leduyanh.bookingfoodshipper.view.history.HistoryFragment
import com.leduyanh.bookingfoodshipper.view.home.HomeFragment
import com.leduyanh.bookingfoodshipper.view.neworder.NewOrderActivity
import com.leduyanh.bookingfoodshipper.view.profile.ProfileFragment
import com.leduyanh.bookingfoodshipper.view.wallet.WalletFragment
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var mSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_home)

        mSocket = IO.socket("http://192.168.1.6:3000/")
        mSocket.connect()

        mSocket.on("server-send-user",onRetrieveUser)

        supportFragmentManager.addFragment(
            R.id.frameFragment,
            HomeFragment(),
            isBackStack = false
        )

        btnMenuHome.setOnClickListener(this)
        btnMenuProfile.setOnClickListener(this)
        btnMenuHistory.setOnClickListener(this)
        btnMenuWallet.setOnClickListener(this)
    }

    fun moveScreen(fragment: Fragment, isBackStack: Boolean){
        supportFragmentManager.addFragment(R.id.containerHome,fragment,isBackStack)
    }

    private val onRetrieveUser = Emitter.Listener {
        runOnUiThread(Runnable {
            kotlin.run {
                val intent = Intent(this,NewOrderActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnMenuHome -> {
                supportFragmentManager.addFragment(
                    R.id.frameFragment,
                    HomeFragment(), isBackStack = false)
                changeStatusCheckedMenu("home")
            }
            R.id.btnMenuWallet ->{
                supportFragmentManager.addFragment(R.id.frameFragment, WalletFragment(), isBackStack = false)
                changeStatusCheckedMenu("wallet")
            }
            R.id.btnMenuHistory ->{
                supportFragmentManager.addFragment(R.id.frameFragment, HistoryFragment(), isBackStack = false)
                changeStatusCheckedMenu("history")
            }
            R.id.btnMenuProfile ->{
                supportFragmentManager.addFragment(
                    R.id.frameFragment,
                    ProfileFragment(), isBackStack = false)
                changeStatusCheckedMenu("profile")
            }
        }
    }

    private fun changeStatusCheckedMenu(menuOption: String) {
        iconMenuHome.setImageResource(R.drawable.icon_home)
        iconMenuWallet.setImageResource(R.drawable.icon_wallet)
        iconMenuHistory.setImageResource(R.drawable.icon_history)
        iconMenuProfile.setImageResource(R.drawable.icon_profile)

        txtMenuHome.setTextColor(Color.WHITE)
        txtMenuHistiory.setTextColor(Color.WHITE)
        txtMenuWallet.setTextColor(Color.WHITE)
        txtMenuProfile.setTextColor(Color.WHITE)

        when(menuOption){
            "home"->{
                iconMenuHome.setImageResource(R.drawable.icon_home_green)
                txtMenuHome.setTextColor(Color.GREEN)
            }
            "wallet"->{
                iconMenuWallet.setImageResource(R.drawable.icon_wallet_green)
                txtMenuWallet.setTextColor(Color.GREEN)
            }
            "history"->{
                iconMenuHistory.setImageResource(R.drawable.icon_history_green)
                txtMenuHistiory.setTextColor(Color.GREEN)
            }
            "profile"->{
                iconMenuProfile.setImageResource(R.drawable.icon_profile_green)
                txtMenuProfile.setTextColor(Color.GREEN)
            }
        }
    }

}
