package com.thepascal.soccerstats

import androidx.core.util.PatternsCompat
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean
    = this.isNotEmpty() &&
        PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean{

    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
    val pattern: Pattern = Pattern.compile(passwordRegex)
    val mMatcher: Matcher = pattern.matcher(this.trim())

    return (this.length >= 8 && mMatcher.find())
}
