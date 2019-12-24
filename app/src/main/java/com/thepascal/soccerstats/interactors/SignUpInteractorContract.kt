package com.thepascal.soccerstats.interactors

import com.thepascal.soccerstats.entities.SignUpEntity

interface SignUpInteractorContract {
    fun signUpUser(signUpEntity: SignUpEntity)
}