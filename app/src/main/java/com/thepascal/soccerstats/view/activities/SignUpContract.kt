package com.thepascal.soccerstats.view.activities

import com.thepascal.soccerstats.entities.SignUpEntity
import com.thepascal.soccerstats.entities.SignUpErrorEntity

interface SignUpContract {
    fun onSuccess(signUpEntity: SignUpEntity)
    fun onFailure(signUpError: SignUpErrorEntity)
}