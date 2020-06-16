package com.leduyanh.bookingfoodshipper.utils

import android.content.Context
import android.content.SharedPreferences
import com.leduyanh.bookingfoodshipper.MyApplication


//import android.preference.PreferenceManager


class SaveSharedPreference(context: Context) {
    private val nameCache = "inforUser"
    private val modeCache = Context.MODE_PRIVATE
    private var preferences: SharedPreferences?


    init {
        preferences = context?.getSharedPreferences(nameCache, modeCache)
    }

    private fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun putString(key: String, value: String) {
        preferences?.edit {
            it.putString(key, value)
        }
    }

    fun getString(pair: Pair<String, String>): String? {
        return preferences?.getString(pair.first, pair.second)!!
    }

    fun putInt(key: String, value: Int) {
        preferences?.edit {
            it.putInt(key, value)
        }
    }


    fun getInt(pair: Pair<String, Int>): Int? {
        return preferences?.getInt(pair.first, pair.second)
    }


    fun putBoolean(key: String, value: Boolean) {
        preferences?.edit {
            it.putBoolean(key, value)
        }
    }


    fun getBoolean(pair: Pair<String, Boolean>): Boolean? {
        return preferences?.getBoolean(pair.first, pair.second)
    }

    fun clear() {
        preferences?.edit()!!.clear().commit();
    }

    companion object {
        val IS_LOGIN = Pair("isLogin", false)
        val ID = Pair("id", 0)
        val USERNAME = Pair("userName", "")
        val EMAIL = Pair("email", "")
        val TOKEN= Pair("token", "")
        val URL_IMAGE = Pair("url_image", "")
        val ID_NEW_ORDER = Pair("id_order_current", -1)

        val DELIVERY = Pair("delivery", false)
        val STATUS_SHIPPER = Pair("status_shipper", 0)
    }
}