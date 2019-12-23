package com.thepascal.soccerstats.interactors

interface SignInInteractorContract {

    fun signInUser(email: String, password: String)
    fun signOutUser()
}