package com.thepascal.soccerstats

import androidx.core.util.PatternsCompat
import java.util.regex.Matcher
import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean = email.isNotEmpty() &&
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

fun isPasswordValid(password: String): Boolean {

    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
    val pattern: Pattern = Pattern.compile(passwordRegex)
    val mMatcher: Matcher = pattern.matcher(password.trim())

    return (password.length >= 8 && mMatcher.find())
}