package com.thepascal.soccerstats.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.thepascal.soccerstats.R
import com.thepascal.soccerstats.data.Bet
import com.thepascal.soccerstats.data.Gamer
import com.thepascal.soccerstats.data.Match
import kotlinx.android.synthetic.main.activity_bet.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class BetActivity : ActivityWithMenu() {

    private lateinit var bet: Bet
    lateinit var league: String

    //lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null
    lateinit var databaseReference: DatabaseReference

    private val mBets: MutableList<Bet> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bet)

        val firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(firebaseAuth.uid!!)

        //let's get the bets if any
        retrieveBets()

        Toast.makeText(this, "size: ${mBets.size}", Toast.LENGTH_LONG).show()

        //setting the toolbar
        setUpToolbar()

        //getting the intent
        val intent = intent

        if (intent.hasExtra("betId")) {
            title = "Edit Bet"
            bet = intent.getParcelableExtra("bet")
            tvBetHomeTeam.text = bet.homeTeam
            tvBetAwayTeam.text = bet.awayTeam
            etBetHomeTeamScore.setText(bet.homeScore)
            etBetAwayTeamScore.setText(bet.awayScore)

            btnSubmitBet.text = getString(R.string.update_bet_text)
        } else {
            val match: Match = intent.getParcelableExtra("match")
            league = intent.getStringExtra("league")

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

        val key: String? = databaseReference.child("Gamer").push().key
        bet.id = key

        //bets.add(Bet(firebaseUser?.uid, ))
        key?.let {
            databaseReference.child("Gamer").child(it).setValue(bet)
        }
    }

    private fun retrieveBets() {
        val rootReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.uid!!)
        val betReference: DatabaseReference = rootReference.child("Gamer")
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
            if (betList[i].equals(bet)) {
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