package com.thepascal.soccerstats.interactors

import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.entities.SignUpEntity
import com.thepascal.soccerstats.presenters.SignUpPresenterContract
import com.thepascal.soccerstats.validateSignUpForm

class SignUpInteractor(private val signUpPresenter: SignUpPresenterContract) : SignUpInteractorContract {

    override fun signUpUser(signUpEntity: SignUpEntity) {
        val errors: MutableList<FormErrors> = validateSignUpForm(signUpEntity)

        if (errors.isEmpty()) {
            signUpPresenter.handleUserSignUp(signUpEntity)
        }
        signUpPresenter.handleUserValidation(errors)
    }
}