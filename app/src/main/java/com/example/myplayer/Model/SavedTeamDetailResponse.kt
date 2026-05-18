package com.example.myplayer.Model


data class SavedTeamDetailResponse(
    val teamId: Long?,
    val matchId: String?,
    val t1Name: String?,
    val t2Name: String?,
    val t1ShortName: String?,
    val t2ShortName: String?,
    val t1Pic: String?,
    val t2Pic: String?,
    val captainName: String?,
    val viceCaptainName: String?,
    val totalPlayers: Int?,
    val createdAt: String?,
    val players: List<SavedTeamPlayerResponse>?
)