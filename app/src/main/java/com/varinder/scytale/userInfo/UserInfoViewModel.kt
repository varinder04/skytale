package com.varinder.scytale.userInfo

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.varinder.scytale.common.ADDRESS
import com.varinder.scytale.common.EMAIL
import com.varinder.scytale.common.MOBILE_NO
import com.varinder.scytale.common.NAME
import com.varinder.scytale.common.PROFILE_IMAGE
import com.varinder.scytale.common.SharedPrefModule
import com.varinder.scytale.common.SingleLiveEvent
import com.varinder.scytale.common.isValidEmail
import com.varinder.scytale.common.isValidName
import com.varinder.scytale.common.isValidPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    var sharedPrefModule: SharedPrefModule,
    application: Application
) : AndroidViewModel(application) {

    private var application = application

    val image by lazy { ObservableField("") }
    val name by lazy { ObservableField("") }
    val mobileNo by lazy { ObservableField("") }
    val address by lazy { ObservableField("") }
    val email by lazy { ObservableField("") }

    init {
        name.set(sharedPrefModule.getPref(application.applicationContext).getString(NAME, ""))
        mobileNo.set(
            sharedPrefModule.getPref(application.applicationContext).getString(MOBILE_NO, "")
        )
        address.set(sharedPrefModule.getPref(application.applicationContext).getString(ADDRESS, ""))
        email.set(sharedPrefModule.getPref(application.applicationContext).getString(EMAIL, ""))
        image.set(
            sharedPrefModule.getPref(application.applicationContext).getString(PROFILE_IMAGE, "")
        )
    }
}