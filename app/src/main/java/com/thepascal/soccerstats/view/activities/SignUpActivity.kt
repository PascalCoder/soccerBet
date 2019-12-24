package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.LeaguesConstants.GAMER_PATH_STRING
import com.thepascal.soccerstats.data.Gamer
import com.thepascal.soccerstats.entities.SignUpEntity
import com.thepascal.soccerstats.entities.SignUpErrorEntity
import com.thepascal.soccerstats.interactors.SignUpInteractor
import com.thepascal.soccerstats.interactors.SignUpInteractorContract
import com.thepascal.soccerstats.presenters.SignUpPresenter
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import com.thepascal.soccerstats.showError
import com.thepascal.soccerstats.toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), SignUpContract {

    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null

    private lateinit var signUpInteractor: SignUpInteractorContract

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpInteractor = SignUpInteractor(SignUpPresenter(this))

        supportActionBar?.title = getString(R.string.sign_up_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            signUpInteractor.signUpUser(
                SignUpEntity(
                    signUpUsername.editText?.text.toString(),
                    signUpEmail.editText?.text.toString(),
                    signUpPassword.editText?.text.toString(),
                    signUpPasswordConfirm.editText?.text.toString()
                )
            )
        }
    }

    override fun onSuccess(signUpEntity: SignUpEntity) {
        firebaseAuth.createUserWithEmailAndPassword(
            signUpEntity.email,
            signUpEntity.password
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    uploadGamerData(signUpEntity.username, signUpEntity.email)
                    toast(getString(R.string.sign_up_success_msg))
                    finish()
                    router.goToSignInView(this@SignUpActivity)
                } else {
                    it.addOnFailureListener { e ->
                        toast(e.message) //e as in exception
                    }
                }
            }
    }

    override fun onFailure(signUpError: SignUpErrorEntity) {
        signUpUsername.showError(signUpError.usernameError)
        signUpEmail.showError(signUpError.emailError)
        signUpPassword.showError(signUpError.passwordError)
        signUpPasswordConfirm.showError(signUpError.passwordRepeatError)
    }

    private fun uploadGamerData(username: String, email: String) {

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference(firebaseAuth.uid!!)

        firebaseUser = firebaseAuth.currentUser
        //val bets: MutableList<Bet> = arrayListOf()
        val gamer = Gamer(
            id = firebaseUser?.uid?.substring(0, 4),
            username = username,
            email = email,
            previousPoints = 0,
            currentPoints = 0
        )
        //bets.add(Bet(firebaseUser?.uid, ))
        databaseReference.child(GAMER_PATH_STRING).setValue(gamer)
    }
}
