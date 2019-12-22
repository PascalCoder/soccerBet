package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract

open class ActivityWithMenu : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        user = firebaseAuth.currentUser

    }

    private fun logOutUser() {
        firebaseAuth.signOut()
        router.goToSignInView(this)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.myBets -> {
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }

            R.id.myAccount -> {
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                logOutUser()
            }
            R.id.settings -> {
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }
            R.id.refresh -> {
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    open fun setUpToolbar(toolbar: Toolbar, title: String = getString(R.string.app_name)) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
    }
}