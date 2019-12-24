package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.view.activities.PasswordResetContract

class PasswordResetPresenter(private val passwordReset: PasswordResetContract?) : PasswordResetPresenterContract {

    override fun handleRecoveryEmail(email: String) {
        passwordReset?.onSuccess(email)
    }

    override fun handleEmailValidation(errors: MutableList<FormErrors>) {
        var emailError = 0

        if (errors.contains(FormErrors.EmailEmpty)) {
            emailError = R.string.reset_password_email_blank_error
        } else if (errors.contains(FormErrors.EmailInvalid)) {
            emailError = R.string.reset_password_email_invalid
        }

        passwordReset?.onFailure(emailError)
    }
}