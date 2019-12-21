package com.thepascal.soccerstats.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Gamer(val id:String? = "0", val username: String?,
                 val email: String?,
                 val previousPoints:Int = 0, val currentPoints:Int = 0,
                 val betList:List<Bet>? = null): Parcelable{ //, val password:String?
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Bet)
    )

    constructor(gamer: Gamer): this(
        gamer.id?.substring(0, 4),
        gamer.username,
        gamer.email,
        gamer.previousPoints,
        gamer.currentPoints
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeInt(previousPoints)
        parcel.writeInt(currentPoints)
        parcel.writeTypedList(betList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gamer> {
        override fun createFromParcel(parcel: Parcel): Gamer {
            return Gamer(parcel)
        }

        override fun newArray(size: Int): Array<Gamer?> {
            return arrayOfNulls(size)
        }
    }

}

data class Bet(var id:String? = "", var homeTeam:String? = "",
               var awayTeam:String? = "",
               var homeScore:Int = 0, var awayScore:Int = 0,
               var matchLeague:String? = "",
               var matchId:String? = "",
               var matchDate:String? = ""): Parcelable, Serializable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeInt(homeScore)
        parcel.writeInt(awayScore)
        parcel.writeString(matchId)
        parcel.writeString(matchDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bet> {
        override fun createFromParcel(parcel: Parcel): Bet {
            return Bet(parcel)
        }

        override fun newArray(size: Int): Array<Bet?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(otherBet: Any?): Boolean {
        if (this === otherBet) return true
        if (otherBet == null || javaClass != otherBet.javaClass) return false
        val bet = otherBet as Bet?
        return homeTeam == bet!!.homeTeam &&
                awayTeam == bet.awayTeam &&
                matchLeague == bet.matchLeague &&
                matchDate == bet.matchDate
    }
}

/*
data class Bet(val gamer: Gamer?, val homeTeam:String?, val awayTeam:String?,
               val homeScore:Int, val awayScore:Int, val matchId:String?,
               val matchDate:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Gamer::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(gamer, flags)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeInt(homeScore)
        parcel.writeInt(awayScore)
        parcel.writeString(matchId)
        parcel.writeString(matchDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bet> {
        override fun createFromParcel(parcel: Parcel): Bet {
            return Bet(parcel)
        }

        override fun newArray(size: Int): Array<Bet?> {
            return arrayOfNulls(size)
        }
    }

}*/
