package com.thepascal.soccerstats.entities

data class SignUpEntity(
    val username: String,
    val email: String,
    val password: String,
    val passwordRepeat: String
)