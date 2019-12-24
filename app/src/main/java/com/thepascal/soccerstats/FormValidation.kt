package com.thepascal.soccerstats

import com.thepascal.soccerstats.entities.SignUpEntity

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

fun validateSignUpForm(signUpEntity: SignUpEntity): MutableList<FormErrors> {
    val errorsList = mutableListOf<FormErrors>()

    if (signUpEntity.username.isBlank()){
        errorsList.add(FormErrors.UsernameEmpty)
    } else if (!isUsernameValid(signUpEntity.username)) {
        errorsList.add(FormErrors.UsernameInvalid)
    }

    if (signUpEntity.email.isBlank()) {
        errorsList.add(FormErrors.EmailEmpty)
    }else if (!isEmailValid(signUpEntity.email)){
        errorsList.add(FormErrors.EmailInvalid)
    }

    if (signUpEntity.password.isBlank()) {
        errorsList.add(FormErrors.PasswordEmpty)
    } else if (!isPasswordValid(signUpEntity.password)){
        errorsList.add(FormErrors.PasswordInvalid)
    } else if (signUpEntity.password != signUpEntity.passwordRepeat){
        errorsList.add(FormErrors.PasswordNotMatching)
    }

    return errorsList
}

fun validateResetPasswordForm(email: String): MutableList<FormErrors> {
    val errorsList = mutableListOf<FormErrors>()

    if (email.isBlank()){
        errorsList.add(FormErrors.EmailEmpty)
    } else if (!isEmailValid(email)){
        errorsList.add(FormErrors.EmailInvalid)
    }

    return errorsList
}