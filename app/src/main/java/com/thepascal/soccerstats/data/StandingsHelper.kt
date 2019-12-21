package com.thepascal.soccerstats.data

import com.google.gson.annotations.SerializedName

data class Away(var wins: Int, var draws: Int,
                @SerializedName("losts")var losses: Int,
                var scores: Int, var conceded: Int, var points: Int,
                @SerializedName("matches_played")var matchesPlayed: Int,
                @SerializedName("goal_difference")var goalDifference: Int)

data class Home(var wins: Int, var draws: Int,
                @SerializedName("losts")var losses: Int,
                var scores: Int, var conceded: Int, var points: Int,
                @SerializedName("matches_played")var matchesPlayed: Int,
                @SerializedName("goal_difference")var goalDifference: Int)

data class Overall(var wins: Int, var draws: Int,
                   @SerializedName("losts")var losses: Int,
                   var scores: Int, var conceded: Int, var points: Int,
                   @SerializedName("matches_played")var matchesPlayed: Int,
                   @SerializedName("goal_difference")var goalDifference: Int)

data class Standing(var identifier: String, var position: String,
                    @SerializedName("team_identifier")
                    var teamIdentifier: String, var team: String,
                    var overall: Overall, var home: Home,
                    var away: Away,
                    @SerializedName("penalization_points")
                    var penalizationPoints: Int)

data class StandingList(var standings: List<Standing>)

data class StandingsData(@SerializedName("data")
                         var standingList: StandingList)