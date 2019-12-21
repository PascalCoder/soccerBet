package com.thepascal.soccerstats.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SoccerApi {

    @GET("v1/leagues/{league}/seasons/{year}/teams") //Ex: league: liga, year:18-19
    fun getTeam(@Path("league") league: String, @Path("year") year: String): Call<TeamList>

    @GET("v1/leagues/{league}/seasons/{year}/standings")
    fun getStandings(@Path("league") league: String?, @Path("year") year: String): Call<StandingsData>

    @GET("v1/leagues/{league}/seasons/{year}/rounds") //Ex: team_identifier=qtjxv9d71ntirsgpjbmeefda4gewdnd9
    fun getTeamStats(@Path("league") league: String, @Path("year") year: String, @Query("team_identifier") teamIdentifier: String): Call<MatchData>
}