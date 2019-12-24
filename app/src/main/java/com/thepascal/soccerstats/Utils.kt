package com.thepascal.soccerstats

import com.google.firebase.auth.FirebaseUser

fun isUserLoggedIn(user: FirebaseUser?): Boolean = (user != null)