package com.thepascal.soccerstats

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
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

fun Context.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun TextView.displayErrorIfAny(errorInt: Int) {
    if (errorInt == 0) {
        remove()
    } else {
        text = context.getString(errorInt)
        unveil()
    }
}

fun View.unveil() {
    visibility = View.VISIBLE
}

fun View.remove() {
    visibility = View.GONE
}

fun TextInputLayout.setDrawableRight(drawable: Int) {
    editText?.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    //editText?.compoundDrawablePadding = 100
}

fun TextInputLayout.showError(errorInt: Int) {
    if (errorInt == 0) {
        error = null
        isErrorEnabled = false
    } else {
        error = context.getString(errorInt)
    }
}
