package com.thepascal.soccerstats.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{
        private const val BASE_URL = "http://soccer.sportsopendata.net/"
        var soccerApi: SoccerApi? = null

        fun initializeRetrofit(){
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            soccerApi = retrofit.create(SoccerApi::class.java)
        }
    }
}