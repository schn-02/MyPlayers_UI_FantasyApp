package com.example.myplayer.UpperNavigationView.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayer.Model.MatchSquadModel
import com.example.myplayer.Model.SaveTeamPlayerRequest
import com.example.myplayer.Model.SaveTeamRequest
import com.example.myplayer.Model.TeamViewCategorized
import com.example.myplayer.Retrofit.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TrackViewModel : ViewModel() {

    private val _selectedPlayerID = mutableStateOf<Set<String>>(emptySet())
    val selectedPlayerID: State<Set<String>> get() = _selectedPlayerID

    val selectedRoles = mutableStateOf<Map<String, String>>(emptyMap())

    private val _selectedCountPerTeam = mutableStateOf<Map<String, Int>>(emptyMap())
    val selectedCountPerTeam: State<Map<String, Int>> get() = _selectedCountPerTeam

    var squadList by mutableStateOf<List<MatchSquadModel>>(emptyList())

    val wickketKeeperCount: State<Int> = derivedStateOf {
        squadList.count {
            it.role.equals("WK-Batsman", true) &&
                    _selectedPlayerID.value.contains(it.id)
        }
    }

    val batsmanCount: State<Int> = derivedStateOf {
        squadList.count {
            it.role.equals("Batsman", true) &&
                    selectedPlayerID.value.contains(it.id)
        }
    }

    val allRounderCount: State<Int> = derivedStateOf {
        squadList.count {
            it.role.equals("Bowling Allrounder", true) &&
                    selectedPlayerID.value.contains(it.id)
        }
    }

    val bowlerCount: State<Int> = derivedStateOf {
        squadList.count {
            it.role.equals("Bowler", true) &&
                    selectedPlayerID.value.contains(it.id)
        }
    }

    fun UpdateSquadList(currentSquad: List<MatchSquadModel>) {
        squadList = currentSquad
    }

    fun togglePlayerSelection(
        PlayerId: String,
        context: Context,
    ) {
        val player = squadList.find { it.id == PlayerId }

        if (player == null) return

        val role = player.role ?: ""

        val teamName: String = if (!player.shortname.isNullOrEmpty()) {
            player.shortname ?: ""
        } else {
            if ((player.teamName?.length ?: 0) >= 4) {
                player.teamName?.substring(0, 4) ?: ""
            } else {
                player.teamName ?: ""
            }
        }

        val count = when (role) {
            "WK-Batsman" -> wickketKeeperCount.value
            "Batsman" -> batsmanCount.value
            "Bowling Allrounder" -> allRounderCount.value
            "Bowler" -> bowlerCount.value
            else -> 0
        }

        val currenTeamCount = _selectedCountPerTeam.value[teamName] ?: 0

        val updated = _selectedPlayerID.value.toMutableSet()

        if (updated.contains(PlayerId)) {

            updated.remove(PlayerId)

            _selectedCountPerTeam.value = _selectedCountPerTeam.value.toMutableMap().apply {
                if (currenTeamCount > 1) {
                    put(teamName, currenTeamCount - 1)
                } else {
                    remove(teamName)
                }
            }

        } else {

            if (updated.size >= 11) {
                Toast.makeText(context, "Max 11 players allowed", Toast.LENGTH_SHORT).show()
                return
            }

            if (count >= 5) {
                Toast.makeText(context, "$role , should be less than 5", Toast.LENGTH_SHORT).show()
                return
            }

            if (currenTeamCount >= 7) {
                Toast.makeText(context, "Only 7 players allowed from $teamName", Toast.LENGTH_SHORT).show()
                return
            }

            updated.add(PlayerId)

            _selectedCountPerTeam.value = _selectedCountPerTeam.value.toMutableMap().apply {
                put(teamName, currenTeamCount + 1)
            }
        }

        _selectedPlayerID.value = updated

        Log.d("SELECTED", "After: ${selectedPlayerID.value}")
        Log.d("MatchCountTeam", "togglePlayerSelection: ${selectedCountPerTeam.value}")
    }

    fun isTeamValid(): Boolean {

        val wk = wickketKeeperCount.value
        val bat = batsmanCount.value
        val ar = allRounderCount.value
        val bowl = bowlerCount.value

        val total = wk + bat + ar + bowl

        return total == 11 && wk >= 1 && bat >= 1 && ar >= 1 && bowl >= 1
    }

    fun getPlayersByIdForTeamPreview(): TeamViewCategorized {

        val selectedId = selectedPlayerID.value
        val allPlayers = squadList

        val selectedPlayers = allPlayers.filter { it.id in selectedId }

        val wk = selectedPlayers.filter {
            it.role.equals("WK-Batsman", ignoreCase = true)
        }

        val bat = selectedPlayers.filter {
            it.role.equals("Batsman", ignoreCase = true)
        }

        val ar = selectedPlayers.filter {
            it.role.equals("Bowling Allrounder", ignoreCase = true)
        }

        val bow = selectedPlayers.filter {
            it.role.equals("Bowler", ignoreCase = true)
        }

        return TeamViewCategorized(
            wk = wk,
            bat = bat,
            bowl = bow,
            all = ar
        )
    }

    fun onSelectRole(role: String, playerId: String) {

        val currentMap = selectedRoles.value.toMutableMap()
        val otherRole = if (role == "C") "VC" else "C"

        when {

            currentMap[role] == playerId -> {
                currentMap.remove(role)
            }

            currentMap[otherRole] == playerId -> {
                currentMap.remove(otherRole)
                currentMap[role] = playerId
            }

            else -> {
                currentMap[role] = playerId
            }
        }

        selectedRoles.value = currentMap
    }

    fun saveTeamToServer(
        matchID: String,
        t1Name: String,
        t2Name: String,
        t1shortName: String,
        t2shortName: String,
        t1Pic: String,
        t2Pic: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        viewModelScope.launch {

            try {

                val user = FirebaseAuth.getInstance().currentUser

                if (user == null) {
                    onError("User not logged in")
                    return@launch
                }

                val token = user.getIdToken(true).await().token

                if (token.isNullOrBlank()) {
                    onError("Token not found")
                    return@launch
                }

                val selectedPlayers = getPlayersByIdForTeamPreview()

                val allPlayers = selectedPlayers.wk +
                        selectedPlayers.bat +
                        selectedPlayers.all +
                        selectedPlayers.bowl

                if (allPlayers.size != 11) {
                    onError("Please select 11 players")
                    return@launch
                }

                val captainPlayerId = selectedRoles.value["C"]
                val viceCaptainPlayerId = selectedRoles.value["VC"]

                if (captainPlayerId.isNullOrBlank()) {
                    onError("Please select Captain")
                    return@launch
                }

                if (viceCaptainPlayerId.isNullOrBlank()) {
                    onError("Please select Vice Captain")
                    return@launch
                }

                if (captainPlayerId == viceCaptainPlayerId) {
                    onError("Captain and Vice Captain cannot be same")
                    return@launch
                }

                val captainPlayer = allPlayers.find { it.id == captainPlayerId }
                val viceCaptainPlayer = allPlayers.find { it.id == viceCaptainPlayerId }

                if (captainPlayer == null) {
                    onError("Captain player not found")
                    return@launch
                }

                if (viceCaptainPlayer == null) {
                    onError("Vice Captain player not found")
                    return@launch
                }

                val playersRequest: List<SaveTeamPlayerRequest> = allPlayers.map { player ->

                    SaveTeamPlayerRequest(
                        playerId = player.id,
                        playerName = player.name,
                        playerRole = player.role,
                        playerTeamName = player.country ?: player.teamName ?: "",
                        playerImg = player.playerImg,
                        teamImg = "",
                        captain = player.id == captainPlayerId,
                        viceCaptain = player.id == viceCaptainPlayerId
                    )
                }

                val request = SaveTeamRequest(
                    matchId = matchID,
                    t1Name = t1Name,
                    t2Name = t2Name,
                    t1ShortName = t1shortName,
                    t2ShortName = t2shortName,
                    t1Pic = t1Pic,
                    t2Pic = t2Pic,
                    captainPlayerId = captainPlayerId,
                    captainName = captainPlayer.name ?: "",
                    viceCaptainPlayerId = viceCaptainPlayerId,
                    viceCaptainName = viceCaptainPlayer.name ?: "",
                    totalPlayers = allPlayers.size,
                    players = playersRequest
                )

                val response = RetrofitClient.AuthData.saveTeam(request)

                if (response.isSuccessful && response.body()?.success == true) {

                    Log.d("SAVE_TEAM", "Success: ${response.body()}")

                    onSuccess(response.body()?.message ?: "Team saved successfully")

                } else {

                    val errorBody = response.errorBody()?.string()

                    Log.e("SAVE_TEAM", "Code: ${response.code()}")
                    Log.e("SAVE_TEAM", "Message: ${response.message()}")
                    Log.e("SAVE_TEAM", "Body: ${response.body()}")
                    Log.e("SAVE_TEAM", "ErrorBody: $errorBody")

                    onError(
                        response.body()?.message
                            ?: errorBody
                            ?: "Failed to save team"
                    )
                }

            } catch (e: Exception) {

                Log.e("SAVE_TEAM", "Exception: ${e.message}", e)

                onError(e.message ?: "Something went wrong")
            }
        }
    }
}