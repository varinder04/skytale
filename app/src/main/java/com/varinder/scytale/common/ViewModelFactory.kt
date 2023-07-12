package com.varinder.scytale.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.varinder.scytale.signUp.SignUpViewModel

class ViewModelFactory(var application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(SharedPrefModule(),application) as T
        }
        throw IllegalArgumentException("UNKNOWN_CLASS_NAME")
        return super.create(modelClass)
    }
}