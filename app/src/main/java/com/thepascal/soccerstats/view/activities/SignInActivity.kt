package com.thepascal.soccerstats.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thepascal.soccerstats.Contract
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.presenter.SignInPresenter
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), Contract.SignInView {

    private lateinit var signInPresenter: SignInPresenter
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setUpToolbar()

        firebaseAuth = FirebaseAuth.getInstance()

        signInPresenter = SignInPresenter(this)

        val text = getString(R.string.sign_up_link)

        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@SignInActivity, "Link clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, 23, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvSignUpText.text = spannableString
        tvSignUpText.movementMethod = LinkMovementMethod.getInstance()

        //val current = Leagues.PREMIER_LEAGUE

        btnLogin.setOnClickListener {
            Toast.makeText(this, "Signing in", Toast.LENGTH_LONG).show()
            /*signInPresenter.signUserIn(etEmail.text.toString(),
                                    etPassword.text.toString())*/
            //Toast.makeText(this@SignInActivity, "${etPassword.text.toString()}", Toast.LENGTH_SHORT).show()
            /*if(!isUserValid(etEmail.text.toString(), etPassword.text.toString())){
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }*/

            signInUser(etEmail.editText?.text.toString(), etPassword.editText?.text.toString())

            Toast.makeText(this, "Signing in ....", Toast.LENGTH_LONG).show()
        }

        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@SignInActivity, PasswordResetActivity::class.java))
            //finish()
        }

    }

    override fun onStart() {
        super.onStart()
        user = firebaseAuth.currentUser

        if (isUserLoggedIn(user)) {
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            finish()
        }

    }

    private fun isUserLoggedIn(user: FirebaseUser?): Boolean = (user != null)

    /*private fun isUserValid(email: String, password: String): Boolean {
        var isUserValid = false
        Toast.makeText(this@SignInActivity, "$email $password", Toast.LENGTH_SHORT).show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        isUserValid = it.isSuccessful
                        Toast.makeText(this@SignInActivity, "$isUserValid", Toast.LENGTH_SHORT).show()
                    }
        return isUserValid
    }*/

    private fun signInUser(email: String, password: String) {
        //Toast.makeText(this@SignInActivity, "$email $password", Toast.LENGTH_SHORT).show()
        if (email == "" || password == "") {
            Toast.makeText(this@SignInActivity, "Please provide email and password!", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login was successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                    finish() //added not tested
                } else Toast.makeText(this, "Could not log in user", Toast.LENGTH_SHORT).show()
            }
    }

    override fun validateUser(username: String, password: String): Boolean {
        return false
    }

    override fun onSuccess() {
        Toast.makeText(this, "Signing in", Toast.LENGTH_LONG).show()
        print("User: $user")
        startActivity(Intent(this, HomeActivity::class.java)) //use router
        finish()
    }

    override fun onFailure() {

    }

    private fun setUpToolbar() {
        setSupportActionBar(signInToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }
}
