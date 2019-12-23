package com.thepascal.soccerstats.interactors

import android.text.TextUtils
import com.thepascal.soccerstats.FormErrors
import com.thepascal.soccerstats.presenters.SignInPresenter
import com.thepascal.soccerstats.validateSignInForm

class SignInInteractor(val signInPresenter: SignInPresenter) : SignInInteractorContract {

    var errorMessage = ""
    //var signInActivity: SignInActivity = signInPresenter.signInView as SignInActivity

    fun signIn(username: String, password: String) {
        //var gamer: Gamer = Gamer(0, username, password)
        if (!isUserValid(username, password)) {
            //Toast.makeText(signInActivity, errorMessage, Toast.LENGTH_LONG).show()
            return
        } else {

        }
        //signInPresenter.onSuccess()
    }

    private fun isUserValid(username: String, password: String): Boolean {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            errorMessage = "Please provide credentials"
            return false
        }
        /*if ( username.equals("admin") && password == "1234"){
            return true
        }else return false*/
        return ((username == "admin") && (password == "1234"))
    }

    override fun signInUser(email: String, password: String) {
        val errors: MutableList<FormErrors> = validateSignInForm(email, password)

        if (errors.isEmpty()) {
            signInPresenter.handleUserSignIn(email, password)
        }
        signInPresenter.handleUserValidation(errors)
    }

    override fun signOutUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    } //Contract.SignInListener
}