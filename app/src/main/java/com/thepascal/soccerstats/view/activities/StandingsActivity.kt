package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.Bundesliga.BUNDESLIGA
import com.thepascal.soccerstats.constants.Bundesliga.BUNDESLIGA_TITLE
import com.thepascal.soccerstats.constants.LaLiga.LIGA
import com.thepascal.soccerstats.constants.LaLiga.LIGA_TITLE
import com.thepascal.soccerstats.constants.LeaguesConstants.LEAGUE_EXTRA
import com.thepascal.soccerstats.constants.PremierLeague.PREMIER_LEAGUE
import com.thepascal.soccerstats.constants.PremierLeague.PREMIER_LEAGUE_TITLE
import com.thepascal.soccerstats.constants.SerieA.SERIE_A
import com.thepascal.soccerstats.constants.SerieA.SERIE_A_TITLE
import com.thepascal.soccerstats.view.fragments.LeagueFragment
import kotlinx.android.synthetic.main.activity_standings.*

class StandingsActivity : ActivityWithMenu() {

    companion object{
        lateinit var league: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        league = intent.getStringExtra(LEAGUE_EXTRA)

        setUpToolbar()

        setUpFragment()
    }

    private fun setUpToolbar(){
        var title = ""

        when(league){
            LIGA -> title = LIGA_TITLE
            PREMIER_LEAGUE -> title = PREMIER_LEAGUE_TITLE
            BUNDESLIGA -> title = BUNDESLIGA_TITLE
            SERIE_A -> title = SERIE_A_TITLE
        }

        setSupportActionBar(standingsToolbar as Toolbar)
        supportActionBar?.title = title
    }

    private fun setUpFragment(){

        val fragment = LeagueFragment.newInstance()
        val bundle = Bundle()
        bundle.putString(LEAGUE_EXTRA, league)

        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }
}
