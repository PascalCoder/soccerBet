package com.thepascal.soccerstats.interactors

interface PasswordResetInteractorContract {
    fun sendRecoveryEmail(email: String)
}