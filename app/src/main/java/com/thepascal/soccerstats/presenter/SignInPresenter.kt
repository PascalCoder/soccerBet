package com.thepascal.soccerstats.presenter

import com.thepascal.soccerstats.Contract
import com.thepascal.soccerstats.data.SignInInteractor

class SignInPresenter(var signInView: Contract.SignInView): Contract.SignInListener {

    private var signInInteractor: SignInInteractor? = null

    init {
        signInInteractor = SignInInteractor(this)
    }

    fun signInUser(username: String, password: String){
        signInInteractor?.signIn(username, password)
    }

    override fun validateUser(username: String, password: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        signInView.onSuccess()
    }

    override fun onFailure() {
        signInView.onFailure()
    }

}