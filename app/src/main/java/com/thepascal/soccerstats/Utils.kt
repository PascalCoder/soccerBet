package com.thepascal.soccerstats

interface SignUpListener{
    fun onSuccessfulSignUp()
    fun onFailedSignUp(email: String, password: String, passwordConfirm: String)
    fun onErrorSignUp()
}

interface SignInListener{
    fun onSuccessfulSignIn()
    fun onFailedSignIn(email: String, password: String)
    fun onErrorSignIn()
}