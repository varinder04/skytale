package com.varinder.scytale.common

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

private const val PHONE_REGEX = "[6-9][0-9]{9}"
fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) {
        return false
    }
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return false
    }
    return true
}

fun isValidName(text: String): Boolean {
    if (text.length < 3) {
        return false
    }
    return true
}

fun isValidPhone(phoneNumber: String): Boolean {
    if (phoneNumber.isEmpty()) {
        return false
    } else if (!Pattern.matches(PHONE_REGEX, phoneNumber)) {
        return false
    }
    return true
}