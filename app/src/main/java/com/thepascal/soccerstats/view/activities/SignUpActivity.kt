package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.thepascal.soccerstats.*
import com.thepascal.soccerstats.constants.LeaguesConstants.GAMER_PATH_STRING
import com.thepascal.soccerstats.data.Gamer
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), SignUpListener {

    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null
    private var errorMessage: String = ""

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.title = getString(R.string.sign_up_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            if (isFormValid()) {
                //Upload data to database
                firebaseAuth.createUserWithEmailAndPassword(
                    etEmailSU.editText?.text.toString(),
                    etPasswordSU.editText?.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            onSuccessfulSignUp()
                            finish()
                            router.goToSignInView(this@SignUpActivity)
                        } else {
                            onFailedSignUp(
                                etEmailSU.editText?.text.toString(),
                                etPasswordSU.editText?.text.toString(),
                                etPasswordConfirm.editText?.text.toString()
                            )
                            tvSignUpError.text = errorMessage
                        }
                    }
            } else {
                onFailedSignUp(
                    etEmailSU.editText?.text.toString(),
                    etPasswordSU.editText?.text.toString(),
                    etPasswordConfirm.editText?.text.toString()
                )
                tvSignUpError.text = errorMessage
            }
        }
    }

    private fun isFormValid(): Boolean {
        if (etEmailSU.editText?.text.toString().isValidEmail()) {
            return if (etPasswordSU.editText?.text.toString().isValidPassword()) { //etPasswordSU.text.toString() != "" || etPasswordSU.text.toString() != null
                if (etPasswordSU.editText?.text.toString() != etPasswordConfirm.editText?.text.toString()) {
                    toast("Passwords do not match")
                    false
                }else{
                    true
                }
            }else{
                toast("Password is not valid!")
                false
            }
        } else {
            toast("Email is not valid!")
            return false
        }

    }

    private fun uploadGamerData(){

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference(firebaseAuth.uid!!)

        firebaseUser = firebaseAuth.currentUser
        //val bets: MutableList<Bet> = arrayListOf()
        val gamer = Gamer(
                    id = firebaseUser?.uid?.substring(0, 4),
                    username = etUsername.editText?.text.toString(),
                    email = etEmailSU.editText?.text.toString(),
                    previousPoints = 0,
                    currentPoints = 0
                        )
        //bets.add(Bet(firebaseUser?.uid, ))
        databaseReference.child(GAMER_PATH_STRING).setValue(gamer)
    }

    override fun onSuccessfulSignUp() {
        Toast.makeText(this, "Sign up was successful!", Toast.LENGTH_SHORT).show()
        //start the Sign In Activity
        uploadGamerData()
    }

    override fun onFailedSignUp(email: String, password: String, passwordConfirm: String) {
        errorMessage = ""
        if (!email.isValidEmail()) {
            errorMessage += "Email is not valid\n"
        }
        if (!password.isValidPassword()) {
            errorMessage += "Password is not valid\n"
        } else {
            if (password != passwordConfirm) {
                errorMessage += "Passwords do not match"
            }
        }
    }

    override fun onErrorSignUp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
