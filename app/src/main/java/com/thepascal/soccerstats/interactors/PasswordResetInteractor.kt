package com.thepascal.soccerstats.interactors

import com.thepascal.soccerstats.presenters.PasswordResetPresenterContract
import com.thepascal.soccerstats.validateResetPasswordForm

class PasswordResetInteractor(private val passwordResetPresenter: PasswordResetPresenterContract) :
    PasswordResetInteractorContract {

    override fun sendRecoveryEmail(email: String) {
        val errors = validateResetPasswordForm(email)

        if (errors.isEmpty()){
            passwordResetPresenter.handleRecoveryEmail(email)
        }
        passwordResetPresenter.handleEmailValidation(errors)
    }
}