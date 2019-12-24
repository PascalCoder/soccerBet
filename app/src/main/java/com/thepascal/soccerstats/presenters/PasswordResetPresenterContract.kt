package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors

interface PasswordResetPresenterContract {
    fun handleRecoveryEmail(email: String)
    fun handleEmailValidation(errors: MutableList<FormErrors>)
}