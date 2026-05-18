package com.example.myplayer.Model


data class SavedTeamPlayerResponse(
    val id: Long?,
    val playerId: String?,
    val playerName: String?,
    val playerRole: String?,
    val playerTeamName: String?,
    val playerImg: String?,
    val teamImg: String?,
    val captain: Boolean?,
    val viceCaptain: Boolean?
)