package com.thepascal.soccerstats.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Match(var identifier: String?,
                 var name: String?,
                 @SerializedName("match_slug")
                 var matchSlug: String?,
                 @SerializedName("date_match")
                 var matchDate: String?,
                 @SerializedName("home_team")
                 var homeTeam: String?,
                 @SerializedName("away_team")
                 var awayTeam: String?,
                 @SerializedName("match_result")
                 var matchResult: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(identifier)
        parcel.writeString(name)
        parcel.writeString(matchSlug)
        parcel.writeString(matchDate)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeString(matchResult)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}

data class MatchData(var data: MatchList)

data class MatchList(@SerializedName("rounds")
                     var matchList: List<Match>)