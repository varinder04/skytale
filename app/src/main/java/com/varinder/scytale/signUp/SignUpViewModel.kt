package com.varinder.scytale.signUp

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.varinder.scytale.common.ADDRESS
import com.varinder.scytale.common.EMAIL
import com.varinder.scytale.common.MOBILE_NO
import com.varinder.scytale.common.NAME
import com.varinder.scytale.common.SharedPrefModule
import com.varinder.scytale.common.SingleLiveEvent
import com.varinder.scytale.common.isValidEmail
import com.varinder.scytale.common.isValidName
import com.varinder.scytale.common.isValidPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    var sharedPrefModule: SharedPrefModule,
    application: Application
) : AndroidViewModel(application) {

    private var application = application

    val name by lazy { ObservableField("") }
    val mobileNo by lazy { ObservableField("") }
    val address by lazy { ObservableField("") }
    val email by lazy { ObservableField("") }
    var onSuccess = SingleLiveEvent<Boolean>()
    var onError = SingleLiveEvent<String>()

    fun onSignUpClick() {

        if (!isValidName(name.get() ?: "")) {
            onError.value = "Please enter valid Name"
            return
        }
        if (!isValidPhone(mobileNo.get() ?: "")) {
            onError.value = "Please enter valid Mobile Number"
            return
        }
        if (!isValidName(address.get() ?: "")) {
            onError.value = "Please enter Address"
            return
        }
        if (!isValidEmail(email.get() ?: "")) {
            onError.value = "Please enter valid Email"
            return
        } else {
            sharedPrefModule.getPref(application.applicationContext).edit()
                .putString(NAME, name.get()).apply()
            sharedPrefModule.getPref(application.applicationContext).edit()
                .putString(MOBILE_NO, mobileNo.get()).apply()
            sharedPrefModule.getPref(application.applicationContext).edit()
                .putString(ADDRESS, address.get()).apply()
            sharedPrefModule.getPref(application.applicationContext).edit()
                .putString(EMAIL, email.get()).apply()
            onSuccess.value = true
        }
    }
}