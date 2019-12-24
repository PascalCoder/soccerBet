package com.thepascal.soccerstats

import androidx.core.util.PatternsCompat
import java.util.regex.Matcher
import java.util.regex.Pattern

fun countGroup(value: String): Int {
    val lowercaseRange = 'a'..'z'
    val uppercaseRange = 'A'..'Z'
    val numberRange = '0'..'9'

    var count = 0

    if (value.any { it in lowercaseRange }) {
        count++
    }
    if (value.any { it in uppercaseRange }) {
        count++
    }
    if (value.any { it in numberRange }) {
        count++
    }

    return count
}

//Username has to be at least 4 characters long and contain a combination of uppercase, lowercase and/or digit
fun isUsernameValid(username: String): Boolean = (countGroup(username) >= 2 && username.length >= 4)

fun isEmailValid(email: String): Boolean = email.isNotEmpty() &&
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

fun isPasswordValid(password: String): Boolean {

    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
    val pattern: Pattern = Pattern.compile(passwordRegex)
    val mMatcher: Matcher = pattern.matcher(password.trim())

    return (password.length >= 8 && mMatcher.find())
}