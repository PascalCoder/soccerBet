package com.thepascal.soccerstats.view.activities

import com.thepascal.soccerstats.entities.SignInErrorEntity

interface SignInContract {
    fun onSuccess(email: String, password: String)
    fun onFailure(signInError: SignInErrorEntity)
}