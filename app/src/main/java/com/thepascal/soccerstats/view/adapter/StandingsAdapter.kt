package com.thepascal.soccerstats.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.data.StandingList
import com.thepascal.soccerstats.data.StandingsData
import com.thepascal.soccerstats.view.activities.StandingsActivity
import com.thepascal.soccerstats.view.activities.TeamStatsActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class StandingsAdapter(standingsData: StandingsData) :
    RecyclerView.Adapter<StandingsAdapter.CustomViewHolder>() {

    private lateinit var context: Context
    private var dataSet: StandingList = standingsData.standingList
    private lateinit var teamId: String
    private var lastPosition: Int = -1

    init {
        //dataSet = standingsData.standingList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.standings.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val goalDiff: Int = dataSet.standings[position].overall.goalDifference
        val goalDiffStr: String = if (goalDiff > 0) "+$goalDiff" else goalDiff.toString()

        holder.tvPosition.text = dataSet.standings[position].position
        holder.tvTeamName.text = dataSet.standings[position].team
        holder.tvPoints.text = dataSet.standings[position].overall.points.toString()
        holder.tvGoalDiff.text = goalDiffStr

        if (goalDiff > 0) holder.tvGoalDiff.setTextColor(Color.rgb(0, 133, 119))
        else if (goalDiff < 0) holder.tvGoalDiff.setTextColor(Color.RED)

        teamId = dataSet.standings[position].teamIdentifier
        holder.tvId.text = teamId

        setAnimation(holder.cardView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int){
        if (position > lastPosition){
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPosition: TextView = itemView.tvPosition
        val tvTeamName: TextView = itemView.tvTeamName
        val tvPoints: TextView = itemView.tvPoints
        val tvGoalDiff: TextView = itemView.tvGoalDiff
        val tvId: TextView = itemView.tvId
        val cardView: CardView = itemView.itemLayoutCardView

        init {
            context = itemView.context
            cardView.setOnClickListener {
                val intent = Intent(itemView.context, TeamStatsActivity::class.java)
                intent.putExtra("id", itemView.tvId.text.toString())
                intent.putExtra("team", itemView.tvTeamName.text.toString())
                intent.putExtra("league", StandingsActivity.league)

                context.startActivity(intent)
            }
        }
    }
}