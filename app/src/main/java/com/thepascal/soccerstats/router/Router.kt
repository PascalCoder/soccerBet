package com.thepascal.soccerstats.router

import android.content.Context
import android.content.Intent
import com.thepascal.soccerstats.constants.LeaguesConstants.LEAGUE_EXTRA
import com.thepascal.soccerstats.view.activities.*

class Router : RouterContract {
    override fun goToHomeView(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

    override fun goToPasswordResetView(context: Context) {
        context.startActivity(Intent(context, PasswordResetActivity::class.java))
    }

    override fun goToSignInView(context: Context) {
        context.startActivity(Intent(context, SignInActivity::class.java))
    }

    override fun goToSignUpView(context: Context) {
        context.startActivity(Intent(context, SignUpActivity::class.java))
    }

    override fun goToStandingsView(context: Context, league: String) {
        context.startActivity(Intent(context, StandingsActivity::class.java).putExtra(LEAGUE_EXTRA, league))
    }
}