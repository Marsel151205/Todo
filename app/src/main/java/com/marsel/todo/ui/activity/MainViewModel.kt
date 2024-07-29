package com.marsel.todo.ui.activity

import androidx.lifecycle.ViewModel
import com.marsel.domain.usecases.userdata.GetFirstLoginUseCase
import com.marsel.domain.usecases.userdata.SetIsFirstLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsFirstLoginUseCase: GetFirstLoginUseCase
) : ViewModel() {

    fun getIsFirstLogin(): Boolean = getIsFirstLoginUseCase()
}