
package com.leduyanh.bookingfoodshipper.view.profile

import android.app.AlertDialog
import android.app.AlertDialog.BUTTON_POSITIVE
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository
import com.leduyanh.bookingfoodshipper.databinding.FragmentProfileBinding
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.main.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_change_password.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel:ProfileViewModel by viewModel()
    private lateinit var dialog : AlertDialog;
    lateinit var sharePreference : SaveSharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = profileViewModel
        profileViewModel.getDataShipper()
        sharePreference = SaveSharedPreference(activity!!)

        val url = sharePreference.getString(SaveSharedPreference.URL_IMAGE)

        Picasso.get() // give it the context
                .load(MyApplication.URL+url) // load the image
                .into(avatar)

        if (progressBar != null) {
            progressBar.visibility = View.GONE
        }

        btnLogout.setOnClickListener() {
            profileViewModel.changeStatusShipper(0)
            sharePreference.clear()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnChangePassword.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_change_password, null);

        builder.setView(view)
            .setTitle("Đổi mật khẩu")
            .setNegativeButton("Hủy", null)
            .setPositiveButton("OK", DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                val newPwd = view.new_password?.text.toString()

                activity!!.window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                progressBar.visibility = View.VISIBLE
                profile_layout.alpha = 0.5f

                val sharePreference : SaveSharedPreference = SaveSharedPreference(activity!!)
                val token = sharePreference.getString(SaveSharedPreference.TOKEN)
                val id = sharePreference.getInt(SaveSharedPreference.ID)
                val shipperRepository = ShipperRepository()

                shipperRepository.updateInfoShipper(token, id, newPwd, object : ICallBack<String> {
                    override fun getData(data: String) {
                        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        progressBar.visibility = View.GONE

                        profile_layout.alpha = 1F

                        Toast.makeText(activity, data + "", Toast.LENGTH_SHORT).show()

                        sharePreference.clear()

                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }

                    override fun getError(mess: String) {

                        progressBar.visibility = View.GONE

                        profile_layout.alpha = 1F

                        Toast.makeText(activity, mess + "", Toast.LENGTH_LONG).show()
                    }
                })
            });

        dialog = builder.create()
        dialog.show()
        dialog.getButton(BUTTON_POSITIVE).isEnabled = false

        view.new_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (!TextUtils.isEmpty(editable) && !view.repeat_password.text.isEmpty()) {
                    // Something into edit text. Enable the button.
                    dialog.getButton(BUTTON_POSITIVE).isEnabled = true
                }

                if (!view.repeat_password.text.isEmpty() && editable.toString() != view.repeat_password.text.toString()) {
                    dialog.getButton(BUTTON_POSITIVE).isEnabled = false
                    view.error_noti.text = "Mật khẩu không khớp"
                } else {
                    view.error_noti.text = ""
                }
            }
        })

        view.repeat_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (!TextUtils.isEmpty(editable) && !view.new_password.text.isEmpty()) {
                    // Something into edit text. Enable the button.
                    dialog.getButton(BUTTON_POSITIVE).isEnabled = true
                }

                if (!view.new_password.text.isEmpty() && editable.toString() != view.new_password.text.toString()) {
                    dialog.getButton(BUTTON_POSITIVE).isEnabled = false
                    view.error_noti.text = "Mật khẩu không khớp"
                } else {
                    view.error_noti.text = ""
                }
            }
        })
    }


}
