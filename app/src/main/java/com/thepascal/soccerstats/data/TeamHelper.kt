package com.thepascal.soccerstats.data

import com.google.gson.annotations.SerializedName

data class Team(var identifier: String,
                @SerializedName("team_slug")
                var teamSlug: String,
                var name: String,
                @SerializedName("team_foundation")
                var teamFoundation: String,
                @SerializedName("team_website")
                var teamWebsite: String)


data class TeamList(var teams: List<Team>)

data class TeamsData(@SerializedName("data")
                     var teamList: TeamList)