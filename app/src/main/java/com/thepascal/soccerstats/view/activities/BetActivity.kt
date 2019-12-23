package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.constants.LeaguesConstants.BET_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.BET_ID_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.GAMER_PATH_STRING
import com.thepascal.soccerstats.constants.LeaguesConstants.LEAGUE_EXTRA
import com.thepascal.soccerstats.constants.LeaguesConstants.MATCH_EXTRA
import com.thepascal.soccerstats.data.Bet
import com.thepascal.soccerstats.data.Match
import com.thepascal.soccerstats.toast
import kotlinx.android.synthetic.main.activity_bet.*

class BetActivity : ActivityWithMenu() {

    private lateinit var bet: Bet
    lateinit var league: String

    //lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null
    private lateinit var databaseReference: DatabaseReference

    private val mBets: MutableList<Bet> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bet)

        val firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(firebaseAuth.uid!!)

        //let's get the bets if any
        retrieveBets()

        toast("size: ${mBets.size}", Toast.LENGTH_LONG)

        //setting the toolbar
        setUpToolbar()

        //getting the intent
        val intent = intent

        if (intent.hasExtra(BET_ID_EXTRA)) {
            title = "Edit Bet"
            bet = intent.getParcelableExtra(BET_EXTRA)
            tvBetHomeTeam.text = bet.homeTeam
            tvBetAwayTeam.text = bet.awayTeam
            etBetHomeTeamScore.setText(bet.homeScore)
            etBetAwayTeamScore.setText(bet.awayScore)

            btnSubmitBet.text = getString(R.string.update_bet_text)
        } else {
            val match: Match = intent.getParcelableExtra(MATCH_EXTRA)
            league = intent.getStringExtra(LEAGUE_EXTRA)

            bet = Bet()
            bet.matchId = match.identifier
            bet.homeTeam = match.homeTeam
            bet.awayTeam = match.awayTeam
            bet.matchLeague = league
            bet.matchDate = match.matchDate

            tvBetHomeTeam.text = match.homeTeam
            tvBetAwayTeam.text = match.awayTeam

        }

        btnSubmitBet.setOnClickListener {
            bet.homeScore = Integer.parseInt(etBetHomeTeamScore.text.toString())
            bet.awayScore = Integer.parseInt(etBetAwayTeamScore.text.toString())

            uploadBetData(bet)

            //Get all bets from database for the specific user
            //Check if bet already exists
            //If true, check date of the game to update bet
            //Else insert/add as new bet in the betList

            onBackPressed()
        }
    }

    private fun uploadBetData(bet: Bet) {

        /*val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = firebaseDatabase.getReference(firebaseAuth.uid!!)*/

        firebaseUser = firebaseAuth.currentUser
        //val bets: MutableList<Bet> = arrayListOf()
        //bet.id = firebaseUser?.providerId
        //bet.id = firebaseUser?.uid?.substring(0, 4)

        val key: String? = databaseReference.child(GAMER_PATH_STRING).push().key //GAMER_PATH_STRING
        bet.id = key

        //bets.add(Bet(firebaseUser?.uid, ))
        key?.let {
            databaseReference.child(GAMER_PATH_STRING).child(it).setValue(bet)
        }
    }

    private fun retrieveBets() {
        val rootReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.uid!!)
        val betReference: DatabaseReference = rootReference.child(GAMER_PATH_STRING)
            .child(rootReference.key!!)
        val betListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mBets.clear()
                dataSnapshot.children.mapNotNullTo(mBets){
                    mBets.add(it.getValue(Bet::class.java)!!)
                    it.getValue(Bet::class.java)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }

        //databaseReference.child("Gamer")
        betReference.addListenerForSingleValueEvent(betListener)
    }

    fun isBetAlreadyPlaced(betList: List<Bet>, bet: Bet): Boolean {
        for (i in betList.indices) {
            if (betList[i] == (bet)) {
                return true
            }
        }
        return false
    }

    private fun setUpToolbar() {

        setSupportActionBar(betToolbar as Toolbar)
        supportActionBar?.title = "Place Bet"
    }
}
