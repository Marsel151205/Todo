package com.marsel.data.local.preferences

import android.content.Context

class PreferencesHelper(
    private val context: Context
) {

    private val sharedPreferences =
        context.getSharedPreferences("todoPreferences", Context.MODE_PRIVATE)

    operator fun invoke() = sharedPreferences


}