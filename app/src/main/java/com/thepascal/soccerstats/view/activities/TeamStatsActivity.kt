package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.thepascal.soccerstats.Leagues
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.LeaguesConstants.ID_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.TEAM_EXTRA
import com.thepascal.soccerstats.data.MatchData
import com.thepascal.soccerstats.data.RetrofitHelper
import com.thepascal.soccerstats.toast
import com.thepascal.soccerstats.view.adapter.TeamStatsAdapter
import kotlinx.android.synthetic.main.activity_team_stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamStatsActivity : ActivityWithMenu() {

    companion object {
        lateinit var mLeague: String
        lateinit var teamId: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_stats)
        setUpToolbar(toolbar = teamStatsToolbar as Toolbar)

        val mIntent = intent
        teamId = mIntent.getStringExtra(ID_EXTRA)
        tvName.text = mIntent.getStringExtra(TEAM_EXTRA)

        RetrofitHelper.initializeRetrofit()
        teamStatsRecyclerView.layoutManager = LinearLayoutManager(this)

        mLeague = StandingsActivity.league

        getTeamResults()
    }

    private fun getTeamResults() {
        RetrofitHelper.soccerApi?.getTeamStats(
            mLeague, Leagues.CURRENT_SEASON,
            teamId
        )
            ?.enqueue(object : Callback<MatchData> {
                override fun onResponse(call: Call<MatchData>, response: Response<MatchData>) {
                    if (response.body() != null) {
                        teamStatsRecyclerView.adapter = TeamStatsAdapter(response.body() as MatchData)
                        //Log.d("MatchData", (response.body() as MatchData).data.matchList[0].matchResult)
                    } else {
                        //Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                        toast("Something went wrong", Toast.LENGTH_LONG)
                    }
                }

                override fun onFailure(call: Call<MatchData>, t: Throwable) {
                    //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    toast(t.message, Toast.LENGTH_LONG)
                }

            })
    }
}
