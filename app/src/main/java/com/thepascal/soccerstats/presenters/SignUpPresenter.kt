package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.entities.SignUpEntity
import com.thepascal.soccerstats.entities.SignUpErrorEntity
import com.thepascal.soccerstats.view.activities.SignUpContract

class SignUpPresenter(private val signUpContract: SignUpContract?) : SignUpPresenterContract {

    override fun handleUserSignUp(signUpEntity: SignUpEntity) {
        signUpContract?.onSuccess(signUpEntity)
    }

    override fun handleUserValidation(errors: MutableList<FormErrors>) {
        var usernameError = 0
        var emailError = 0
        var passwordError = 0
        var passwordRepeatError = 0

        if (errors.contains(FormErrors.UsernameEmpty)) {
            usernameError = R.string.sign_up_blank_username
        } else if (errors.contains(FormErrors.UsernameInvalid)) {
            usernameError = R.string.sign_up_invalid_username
        }

        if (errors.contains(FormErrors.EmailEmpty)) {
            emailError = R.string.sign_up_blank_email
        } else if (errors.contains(FormErrors.EmailInvalid)) {
            emailError = R.string.sign_up_invalid_email
        }

        if (errors.contains(FormErrors.PasswordEmpty)) {
            passwordError = R.string.sign_up_blank_password
        } else if (errors.contains(FormErrors.PasswordInvalid)) {
            passwordError = R.string.sign_up_invalid_password
        } else if (errors.contains(FormErrors.PasswordNotMatching)) {
            passwordRepeatError = R.string.sign_up_password_not_matching
        }

        signUpContract?.onFailure(
            SignUpErrorEntity(
                usernameError,
                emailError,
                passwordError,
                passwordRepeatError
            )
        )
    }
}