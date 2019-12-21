package com.thepascal.soccerstats.view.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepascal.soccerstats.Leagues

import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.data.RetrofitHelper
import com.thepascal.soccerstats.data.StandingsData
import com.thepascal.soccerstats.view.adapter.StandingsAdapter
import kotlinx.android.synthetic.main.fragment_league.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueFragment : Fragment() {

    private var league: String? = null
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): LeagueFragment {
            return LeagueFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_league, container, false)

        RetrofitHelper.initializeRetrofit()
        recyclerView = rootView.leagueRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        league = arguments?.getString("league")

        getLeagueStandings()

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toast.makeText(context, "Just attached!! $league", Toast.LENGTH_LONG).show()
    }

    private fun getLeagueStandings() {
        RetrofitHelper.soccerApi?.getStandings(league, Leagues.CURRENT_SEASON)
            ?.enqueue(object : Callback<StandingsData> {

                override fun onResponse(call: Call<StandingsData>, response: Response<StandingsData>) {
                    if (response.body() != null) {
                        Log.d("LeagueFragment", (response.body() as StandingsData).standingList.standings[0].team)
                        recyclerView.adapter = StandingsAdapter(response.body() as StandingsData)
                    } else {
                        Toast.makeText(context, "Data seems to be empty!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<StandingsData>, t: Throwable) {
                    //Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    Log.d("Data Error", t.message)
                }

            })

    }
}
