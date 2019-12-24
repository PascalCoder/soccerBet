package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.interactors.PasswordResetInteractor
import com.thepascal.soccerstats.interactors.PasswordResetInteractorContract
import com.thepascal.soccerstats.presenters.PasswordResetPresenter
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import com.thepascal.soccerstats.showError
import com.thepascal.soccerstats.toast
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordResetActivity : AppCompatActivity(), PasswordResetContract {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var passwordResetInteractor: PasswordResetInteractorContract

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        passwordResetInteractor = PasswordResetInteractor(PasswordResetPresenter(this))

        setUpToolbar()

        firebaseAuth = FirebaseAuth.getInstance()

        passwordResetButton.setOnClickListener {
            passwordResetInteractor.sendRecoveryEmail(passwordResetEmail.editText?.text.toString())
        }
    }

    override fun onSuccess(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    this@PasswordResetActivity.toast(
                        getString(R.string.reset_password_recovery_email_sent),
                        Toast.LENGTH_LONG
                    )
                    router.goToSignInView(this@PasswordResetActivity)
                    finish()
                } else {
                    this@PasswordResetActivity.toast(
                        getString(R.string.reset_password_email_error),
                        Toast.LENGTH_LONG
                    )
                }
            }
    }

    override fun onFailure(passwordResetError: Int) {
        passwordResetEmail.showError(passwordResetError)
    }

    private fun setUpToolbar() {

        setSupportActionBar(resetPasswordToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.reset_password_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
