package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.entities.SignUpEntity

interface SignUpPresenterContract {

    fun handleUserSignUp(signUpEntity: SignUpEntity)
    fun handleUserValidation(errors: MutableList<FormErrors>)
}