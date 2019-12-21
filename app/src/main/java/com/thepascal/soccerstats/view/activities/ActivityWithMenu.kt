package com.thepascal.soccerstats.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thepascal.soccerstats.R

open class ActivityWithMenu : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null

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
        startActivity(Intent(this, SignInActivity::class.java))
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
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
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