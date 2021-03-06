package com.thepascal.soccerstats.view.activities

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.Bundesliga.BUNDESLIGA
import com.thepascal.soccerstats.constants.LaLiga.LIGA
import com.thepascal.soccerstats.constants.PremierLeague.PREMIER_LEAGUE
import com.thepascal.soccerstats.constants.SerieA.SERIE_A
import com.thepascal.soccerstats.router.Router
import com.thepascal.soccerstats.router.RouterContract
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null

    //Using router for the navigation between Activities to
    //satisfy the separation of concern
    private val router: RouterContract by lazy { Router() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpToolbar()

        firebaseAuth = FirebaseAuth.getInstance()

        btnSignOut.setOnClickListener {
            logOutUser()
        }

        cvLaLiga.setOnClickListener {
            getLeagueStandings(it.context, LIGA)
        }
        cvPremierLeague.setOnClickListener {
            getLeagueStandings(it.context, PREMIER_LEAGUE)
        }
        cvBundesliga.setOnClickListener {
            getLeagueStandings(it.context, BUNDESLIGA)
        }
        cvSerieA.setOnClickListener {
            getLeagueStandings(it.context, SERIE_A)
        }
    }

    private fun getLeagueStandings(context: Context, league: String){
        router.goToStandingsView(context, league)
    }

    override fun onStart() {
        super.onStart()

        user = firebaseAuth.currentUser

    }

    private fun logOutUser(){
        firebaseAuth.signOut()
        router.goToSignInView(this@HomeActivity)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.myBets ->{
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }

            R.id.myAccount ->{
                Toast.makeText(this, "Working on this functionality", Toast.LENGTH_SHORT).show()
            }
            R.id.logout ->{
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

    private fun setUpToolbar() {

        setSupportActionBar(homeToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    /*
        User/Gamer
        email:String, password:String, password_confirm:String, points/coins:Double, bets_placed:Int, matches:List<Match>,
        logged_in:Boolean

        Match
        home_team:String, away_team:String, home_team_score:Int, away_team_score:Int
     */
}
