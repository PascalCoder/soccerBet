package com.thepascal.soccerstats.view.activities

interface PasswordResetContract {
    fun onSuccess(email: String)
    fun onFailure(passwordResetError: Int)
}