package com.dfmovies.android.authentication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dfmovies.android.core.util.extension.SingleLiveEvent
import com.dfmovies.android.core.util.extension.isValidEmail
import com.dfmovies.android.core.viewmodel.ReactiveBaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ReactiveBaseViewModel() {

    private val showEmailValidationErrorLiveData: MutableLiveData<Boolean> =
        SingleLiveEvent()

    private val showPasswordEmptyErrorLiveData: MutableLiveData<Boolean> =
        SingleLiveEvent()

    private val allInputsAreValidLiveData: MutableLiveData<Boolean> =
        SingleLiveEvent()

    fun getShowEmailValidationErrorLiveData(): LiveData<Boolean> = showEmailValidationErrorLiveData

    fun getShowPasswordEmptyErrorLiveData(): LiveData<Boolean> = showPasswordEmptyErrorLiveData

    fun getAllInputsAreValidLiveData(): LiveData<Boolean> = allInputsAreValidLiveData

    private fun setShowEmailValidationErrorLiveData() {
        showEmailValidationErrorLiveData.value = true
    }


    private fun setShowPasswordEmptyErrorLiveData() {
        showPasswordEmptyErrorLiveData.value = true
    }

    private fun setAllInputsAreValidLiveData() {
        allInputsAreValidLiveData.value = true
    }

    fun login(
        email: String,
        password: String
    ) {
        when {
            email.isValidEmail() && password.isNotEmpty() -> {
                setAllInputsAreValidLiveData()
            }
            email.isValidEmail().not() || email.isEmpty() -> {
                setShowEmailValidationErrorLiveData()
            }
            password.isEmpty() -> {
                setShowPasswordEmptyErrorLiveData()
            }
        }
    }
}