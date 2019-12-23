package com.thepascal.soccerstats.presenters

import com.thepascal.soccerstats.FormErrors

interface SignInPresenterContract {

    fun handleUserSignIn(email: String, password: String)
    fun handleUserValidation(errors: MutableList<FormErrors>)
    fun handleUserSignOut()
}