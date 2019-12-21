package com.thepascal.soccerstats

interface Contract {

    interface SignInView{

        fun validateUser(username: String, password: String): Boolean
        fun onSuccess()
        fun onFailure()

    }

    interface SignInListener{

        fun validateUser(username: String, password: String): Boolean
        fun onSuccess()
        fun onFailure()

    }
}