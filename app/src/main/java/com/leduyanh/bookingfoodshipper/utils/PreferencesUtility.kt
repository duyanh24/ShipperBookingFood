package com.leduyanh.bookingfoodshipper.utils

enum class PreferencesUtility {
    LOGIN_STATUS {
        override fun toString(): String {
            return "login_status";
        }
    },

    TOKEN {
        override fun toString(): String {
            return "token";
        }
    }
}