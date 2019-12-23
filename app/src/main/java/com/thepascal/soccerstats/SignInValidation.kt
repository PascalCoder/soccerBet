package com.thepascal.soccerstats

fun validateSignInForm(email: String, password: String): MutableList<FormErrors> {
    val errorsList = mutableListOf<FormErrors>()

    if (email.isBlank()) {
        errorsList.add(FormErrors.EmailEmpty)
    } else if (!isEmailValid(email)) {
        errorsList.add(FormErrors.EmailInvalid)
    }

    if (password.isBlank()) {
        errorsList.add(FormErrors.PasswordEmpty)
    }

    return errorsList
}