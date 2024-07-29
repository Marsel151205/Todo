package com.marsel.data.local.preferences.userdata

import com.marsel.data.local.preferences.PreferencesHelper

class UserDataPreferences(
    private val preferencesHelper: PreferencesHelper
) {

    var isFirstLogin: Boolean
        set(value) = preferencesHelper().edit().putBoolean("isFirstLogin", value).apply()
        get() = preferencesHelper().getBoolean("isFirstLogin", false)
}