package com.marsel.domain.repositories

interface UserDataRepository {

    fun getIsFirstLogin(): Boolean

    fun setIsFirstLogin(isLogin: Boolean)
}