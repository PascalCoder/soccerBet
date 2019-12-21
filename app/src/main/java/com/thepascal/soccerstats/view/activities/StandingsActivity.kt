package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.thepascal.soccerstats.Leagues
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.view.fragments.LeagueFragment
import kotlinx.android.synthetic.main.activity_standings.*

class StandingsActivity : ActivityWithMenu() {

    companion object{
        lateinit var league: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        league = intent.getStringExtra("league")

        setUpToolbar()

        setUpFragment()
    }

    private fun setUpToolbar(){
        var title = ""

        when(league){
            Leagues.LIGA -> title = Leagues.LIGA_TITLE
            Leagues.PREMIER_LEAGUE -> title = Leagues.PREMIER_LEAGUE_TITLE
            Leagues.BUNDESLIGA -> title = Leagues.BUNDESLIGA_TITLE
            Leagues.SERIE_A -> title = Leagues.SERIE_A_TITLE
        }

        setSupportActionBar(standingsToolbar as Toolbar)
        supportActionBar?.title = title
    }

    private fun setUpFragment(){

        val fragment = LeagueFragment.newInstance()
        val bundle = Bundle()
        bundle.putString("league", league)

        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }
}
