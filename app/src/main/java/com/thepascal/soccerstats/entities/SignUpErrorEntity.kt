package com.thepascal.soccerstats.entities

data class SignUpErrorEntity(
    val usernameError: Int = 0,
    val emailError: Int = 0,
    val passwordError: Int = 0,
    val passwordRepeatError: Int = 0
)