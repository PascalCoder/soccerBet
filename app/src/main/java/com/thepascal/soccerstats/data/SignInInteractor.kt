package com.thepascal.soccerstats.data

import android.text.TextUtils
import android.widget.Toast
import com.thepascal.soccerstats.presenter.SignInPresenter
import com.thepascal.soccerstats.view.activities.SignInActivity

class SignInInteractor(val signInListener: SignInPresenter) { //Contract.SignInListener

    var errorMessage = ""
    var signInActivity: SignInActivity = signInListener.signInView as SignInActivity

    fun signIn(username: String, password: String) {
        //var gamer: Gamer = Gamer(0, username, password)
        if(!isUserValid(username, password)){
            Toast.makeText(signInActivity, errorMessage, Toast.LENGTH_LONG).show()
            return
        }else
            signInListener.onSuccess()
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
}