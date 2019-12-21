package com.thepascal.soccerstats.router

import android.content.Context

interface RouterContract {

    fun goToHomeView(context: Context)
    fun goToPasswordResetView(context: Context)
    fun goToSignInView(context: Context)
    fun goToSignUpView(context: Context)
    fun goToStandingsView(context: Context, league: String)
}