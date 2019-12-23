package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.entities.SignInErrorEntity
import com.thepascal.soccerstats.interactors.SignInInteractor
import com.thepascal.soccerstats.interactors.SignInInteractorContract
import com.thepascal.soccerstats.presenters.SignInPresenter
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import com.thepascal.soccerstats.showError
import com.thepascal.soccerstats.toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), SignInContract {

    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null

    private lateinit var signInInteractorContract: SignInInteractorContract

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInInteractorContract = SignInInteractor(SignInPresenter(this))

        setUpToolbar()

        firebaseAuth = FirebaseAuth.getInstance()

        val text = getString(R.string.sign_up_link)

        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                this@SignInActivity.toast("Link clicked", Toast.LENGTH_LONG)
                router.goToSignUpView(this@SignInActivity)
            }
        }

        spannableString.setSpan(clickableSpan, 23, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvSignUpText.text = spannableString
        tvSignUpText.movementMethod = LinkMovementMethod.getInstance()

        //val current = Leagues.PREMIER_LEAGUE

        signInButton.setOnClickListener {
            toast("Signing in", Toast.LENGTH_LONG)

            //signInUser(etEmail.editText?.text.toString(), etPassword.editText?.text.toString())

            signInInteractorContract.signInUser(
                signInEmail.editText?.text.toString(),
                signInPassword.editText?.text.toString()
            )
        }

        tvForgotPassword.setOnClickListener {
            router.goToPasswordResetView(this@SignInActivity)
            //finish()
        }

    }

    override fun onStart() {
        super.onStart()
        user = firebaseAuth.currentUser

        if (isUserLoggedIn(user)) {
            router.goToHomeView(this@SignInActivity)
            finish()
        }

    }

    private fun isUserLoggedIn(user: FirebaseUser?): Boolean = (user != null)

    /*private fun signInUser(email: String, password: String) {
        //Toast.makeText(this@SignInActivity, "$email $password", Toast.LENGTH_SHORT).show()
        if (email == "" || password == "") {
            this@SignInActivity.toast( getString(R.string.sign_in_blank_email))
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Login was successful")
                    router.goToHomeView(this@SignInActivity)
                    finish() //added not tested
                } else toast("Could not log in user")
            }
    }*/

    override fun onSuccess(email: String, password: String) {
        toast("Signing in", Toast.LENGTH_LONG)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Login was successful")
                    router.goToHomeView(this@SignInActivity)
                    finish() //added not tested
                } else toast("Could not log in user")
            }
        Log.d("User", "$user")
        router.goToHomeView(this@SignInActivity)
        finish()
    }

    override fun onFailure(signInError: SignInErrorEntity) {
        signInEmail.showError(signInError.emailError)
        signInPassword.showError(signInError.passwordError)
    }

    private fun setUpToolbar() {
        setSupportActionBar(signInToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }
}
