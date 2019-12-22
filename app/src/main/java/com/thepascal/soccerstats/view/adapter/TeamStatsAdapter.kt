package com.thepascal.soccerstats.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.LeaguesConstants.DATE_TIME_PATTERN
import com.thepascal.soccerstats.constants.LeaguesConstants.LEAGUE_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.MATCH_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.POSITION_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.ROUND_ID_EXTRA
import com.thepascal.soccerstats.data.Match
import com.thepascal.soccerstats.data.MatchData
import com.thepascal.soccerstats.view.activities.BetActivity
import com.thepascal.soccerstats.view.MatchResultModel
import com.thepascal.soccerstats.view.activities.TeamStatsActivity
import kotlinx.android.synthetic.main.team_stats_item_layout.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TeamStatsAdapter(matchData: MatchData) : RecyclerView.Adapter<TeamStatsAdapter.CustomViewHolder>() {

    companion object {
        const val REQUEST_CODE = 1
    }

    //lateinit var match: Match

    //Let's get today's date
    private val today = Date()
    private var lastPosition = -1

    private var dataSet = matchData.data
    private lateinit var matchResultModel: MatchResultModel

    lateinit var context: Context

    init {
        /*var calendar = Calendar.getInstance()
        var date = Date()*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_stats_item_layout, parent, false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.matchList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val matchDataSet = dataSet.matchList[position]
        val matchResult = matchDataSet.matchResult
        holder.homeTeam.text = matchDataSet.homeTeam
        holder.awayTeam.text = matchDataSet.awayTeam

        /*if (matchResultModel.homeTeamScore.isNotBlank()){
            holder.betButton.isEnabled = false
        }*/
        holder.betButton.visibility = View.VISIBLE
        holder.matchDay.visibility = View.GONE

        matchResultModel = getMatchResult(matchResult, holder.betButton)
        holder.homeTeamScore.text = matchResultModel.homeTeamScore
        holder.awayTeamScore.text = matchResultModel.awayTeamScore

        //The betting button is disabled at the time the game starts
        val matchDate = getDateFromString(matchDataSet.matchDate)
        if (matchDate.compareTo(today) <= 0) {
            holder.betButton.isEnabled = false
        }

        //Display match day if the Bet button is enabled
        if (holder.betButton.isEnabled) {
            holder.matchDay.text = matchDate.toString()
            holder.matchDay.visibility = View.VISIBLE
        }

        setAnimation(holder.cardView, position)

        holder.betButton.setOnClickListener {
            val match: Match = dataSet.matchList[holder.adapterPosition]

            val intent = Intent(this.context, BetActivity::class.java)
            intent.putExtra(MATCH_EXTRA, match)
            intent.putExtra(LEAGUE_EXTRA, TeamStatsActivity.mLeague)
            intent.putExtra(ROUND_ID_EXTRA, match.identifier)
            intent.putExtra(POSITION_EXTRA, holder.adapterPosition)

            (this.context as Activity).startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onViewDetachedFromWindow(holder: CustomViewHolder) {
        //super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    private fun getMatchResult(matchResult: String?, betButton: Button): MatchResultModel {

        val matchResultModel = MatchResultModel()

        if (matchResult != null && matchResult.length > 1) {
            matchResultModel.homeTeamScore = matchResult.substring(0, matchResult.indexOf("-"))
            matchResultModel.awayTeamScore = matchResult.substring(matchResult.indexOf("-") + 1)

            betButton.isEnabled = false

        } else {
            betButton.visibility = View.VISIBLE
            betButton.isEnabled = true
        }

        return matchResultModel
    }

    private fun getDateFromString(dateStr: String?): Date {
        val parser = SimpleDateFormat(DATE_TIME_PATTERN, Locale.US)
        parser.timeZone = TimeZone.getDefault()

        lateinit var parsedDate: Date

        try {
            parsedDate = parser.parse(dateStr)
        } catch (pe: ParseException) {
            pe.printStackTrace()
        }

        return parsedDate
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val homeTeam = itemView.tvHomeTeam as TextView
        val awayTeam = itemView.tvAwayTeam as TextView
        val homeTeamScore = itemView.tvHomeTeamScore as TextView
        val awayTeamScore = itemView.tvAwayTeamScore as TextView
        val matchDay = itemView.tvMatchDay as TextView
        val betButton = itemView.btnBet as Button
        val cardView = itemView.statsCardView as CardView

        init {
            context = itemView.context
        }
    }
}