package com.thepascal.soccerstats.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.isValidEmail
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordResetActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)
        //supportActionBar?.hide()
        //setSupportActionBar(resetPasswordToolbar)
        supportActionBar?.title = getString(R.string.reset_password_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener {

            val email = etRecoverEmail.editText?.text.toString()

            //validate email
            if (email.isBlank()){
                etRecoverEmail.error = "Please provide your email. This field is required. You should provide something. Thanks!"
                etRecoverEmail.editText?.setHintTextColor(Color.parseColor("Gray"))
                //return@setOnClickListener
            }else if(email.isValidEmail()) {
                etRecoverEmail.error = ""
                //check if email exists in our database
                //redirect user or display error if any
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this@PasswordResetActivity,
                                "A recovery link has been sent to your email.",
                                Toast.LENGTH_LONG).show()
                            //startActivity(Intent(this@PasswordResetActivity, SignInActivity::class.java))
                            router.goToSignInView(this@PasswordResetActivity)
                            finish()
                        }else{
                            Toast.makeText(this@PasswordResetActivity,
                                "This email was not recognized.",
                                Toast.LENGTH_LONG).show()
                        }
                    }

                //Toast.makeText(this@PasswordResetActivity, "Your email is valid :)", Toast.LENGTH_LONG).show()
            }else{
                etRecoverEmail.error = "Please check the format of your email."
            }

        }
    }

}
