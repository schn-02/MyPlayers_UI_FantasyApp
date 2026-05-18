package com.example.myplayer.Model



data class SaveTeamRequest(
    val matchId: String,
    val t1Name: String,
    val t2Name: String,
    val t1ShortName: String,
    val t2ShortName: String,
    val t1Pic: String,
    val t2Pic: String,
    val captainPlayerId: String,
    val captainName: String,
    val viceCaptainPlayerId: String,
    val viceCaptainName: String,
    val totalPlayers: Int,
    val players: List<SaveTeamPlayerRequest>
)