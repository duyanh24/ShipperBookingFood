package com.leduyanh.bookingfoodshipper.view.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.data.models.shipper.ShipperLoginResponse
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.Login
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btnLogin.setOnClickListener {
            val userName : String = username.text.toString()
            val pwd : String = password.text.toString()

            if (userName.isEmpty()) username.setHintTextColor(getColor(R.color.colorRed))
            if (pwd.isEmpty()) password.setHintTextColor(getColor(R.color.colorRed))

            if (userName.isEmpty() || pwd.isEmpty()) {
                notification.text = "Vui lòng nhập đầy đủ thông tin!"

                Handler().postDelayed({
                    notification.text = ""
                }, 2000)
                return@setOnClickListener
            }
            //else {
                // create progress drawable
                val progressDrawable = CircularProgressDrawable(this).apply {
                    // let's use large style just to better see one issue
                    setStyle(CircularProgressDrawable.LARGE)
                    setColorSchemeColors(Color.WHITE)

                    //bounds definition is required to show drawable correctly
                    val size = (centerRadius + strokeWidth).toInt() * 2
                    setBounds(0, 0, size, size)
                }

                // create a drawable span using our progress drawable
                val drawableSpan = object : DynamicDrawableSpan() {

                    override fun getDrawable() = progressDrawable
                }

                // create a SpannableString like "Loading [our_progress_bar]"
                val spannableString = SpannableString(" ").apply {

                    setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                //start progress drawable animation
                progressDrawable.start()

                val callback = object : Drawable.Callback {
                    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
                    }

                    override fun invalidateDrawable(who: Drawable) {
                        btnLogin.invalidate()
                    }

                    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
                    }
                }

                progressDrawable.callback = callback

                btnLogin.text = spannableString
                btnLogin.isEnabled = false
                btnLogin.setBackgroundResource(R.drawable.btn_grey_login)

                val action : Login = Login()

                action.login(userName, pwd, object : ICallBack<ShipperLoginResponse> {
                    override fun getData(data: ShipperLoginResponse) {
                        if (data.success) {
                            val sharePreference : SaveSharedPreference = SaveSharedPreference(this@LoginActivity)

                            sharePreference.putBoolean(SaveSharedPreference.IS_LOGIN.first, true)
                            sharePreference.putString(SaveSharedPreference.TOKEN.first, data.token)

                            sharePreference.putInt(SaveSharedPreference.ID.first, data.shipper.shipperId)
                            sharePreference.putString(SaveSharedPreference.USERNAME.first, data.shipper.name)
                            sharePreference.putString(SaveSharedPreference.EMAIL.first, data.shipper.email)
                            sharePreference.putString(SaveSharedPreference.URL_IMAGE.first, data.shipper.urlImage)

                            val intent : Intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)

                        } else {
                            notification.text = "Tài khoản hoặc mật khẩu không chính xác"

                            username.text.clear()
                            password.text.clear()

                            btnLogin.text = "Đăng nhập"
                            btnLogin.isEnabled = true
                            btnLogin.setBackgroundResource(R.drawable.btn_login)

                            Handler().postDelayed({
                                notification.text = ""
                            }, 2000)
                        }
                    }

                    override fun getError(mess: String) {
                        Toast.makeText(MyApplication.instance,mess + "", Toast.LENGTH_LONG).show()
                        btnLogin.text = "Đăng nhập"
                        btnLogin.isEnabled = true
                        btnLogin.setBackgroundResource(R.drawable.btn_login)
                    }
                })
            //}
        }

        username.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                username.setHintTextColor(getResources().getColor(R.color.colorLightSeaGreen))
                password.setHintTextColor(getResources().getColor(R.color.colorLightSeaGreen))
            }
        }

        password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                username.setHintTextColor(getResources().getColor(R.color.colorLightSeaGreen))
                password.setHintTextColor(getResources().getColor(R.color.colorLightSeaGreen))
            }
        }
    }
}
