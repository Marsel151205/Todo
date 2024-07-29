package com.marsel.domain.usecases.userdata

import com.marsel.domain.repositories.UserDataRepository
import javax.inject.Inject

class SetIsFirstLoginUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {

    operator fun invoke(isLogin: Boolean) {
        userDataRepository.setIsFirstLogin(isLogin)
    }
}