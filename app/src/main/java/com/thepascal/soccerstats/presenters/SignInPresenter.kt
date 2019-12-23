package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.entities.SignInErrorEntity
import com.thepascal.soccerstats.view.activities.SignInContract

class SignInPresenter(private val signInContract: SignInContract?) : SignInPresenterContract {

    override fun handleUserSignIn(email: String, password: String) {
        signInContract?.onSuccess(email, password)
    }

    override fun handleUserValidation(errors: MutableList<FormErrors>) {
        var emailError = 0
        var passwordError = 0

        if (errors.contains(FormErrors.EmailEmpty)) {
            emailError = R.string.sign_in_blank_email
        } else if (errors.contains(FormErrors.EmailInvalid)){
            emailError = R.string.sign_in_invalid_email
        }

        if (errors.contains(FormErrors.PasswordEmpty)){
            passwordError = R.string.sign_in_blank_password
        }

        signInContract?.onFailure(SignInErrorEntity(emailError, passwordError))
    }

    override fun handleUserSignOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}