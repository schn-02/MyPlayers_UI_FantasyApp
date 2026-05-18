package com.example.myplayer.Model


data class SaveTeamResponse(
    val success: Boolean,
    val message: String,
    val teamId: Long?
)