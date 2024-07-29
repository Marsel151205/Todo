package com.marsel.data.repositories

import com.marsel.data.local.preferences.userdata.UserDataPreferences
import com.marsel.domain.repositories.UserDataRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userDataPreferences: UserDataPreferences
) : UserDataRepository {

    override fun getIsFirstLogin() = userDataPreferences.isFirstLogin

    override fun setIsFirstLogin(isLogin: Boolean) {
        userDataPreferences.isFirstLogin = isLogin
    }
}