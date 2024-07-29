package com.marsel.todo.ui.fragments.onboarding

import androidx.lifecycle.ViewModel
import com.marsel.domain.usecases.userdata.GetFirstLoginUseCase
import com.marsel.domain.usecases.userdata.SetIsFirstLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setIsFirstLoginUseCase: SetIsFirstLoginUseCase,
) : ViewModel() {

    fun setIsFirstLogin(isFirstLogin: Boolean) {
        setIsFirstLoginUseCase(isFirstLogin)
    }
}