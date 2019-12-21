package com.thepascal.soccerstats.router

import android.content.Context
import android.content.Intent
import com.thepascal.soccerstats.view.activities.HomeActivity

class Router: RouterContract {
    override fun goToHomeView(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

    override fun goToPasswordResetView(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToSignInView(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToSignUpView(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToStandingsView(context: Context, league: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}