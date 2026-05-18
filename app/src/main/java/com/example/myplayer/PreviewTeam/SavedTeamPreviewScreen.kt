package com.example.myplayer.PreviewTeam

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myplayer.Model.SavedTeamPlayerResponse
import com.example.myplayer.R
import com.example.myplayer.UpperNavigationView.viewModel.SavedTeamPreviewViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SavedTeamPreviewScreen(
    teamId: String,
    navController: NavController,
    savedTeamPreviewViewModel: SavedTeamPreviewViewModel = viewModel()
) {

    val teamDetails by savedTeamPreviewViewModel.teamDetails.collectAsState()
    val isLoading by savedTeamPreviewViewModel.isLoading.collectAsState()

    LaunchedEffect(teamId) {
        savedTeamPreviewViewModel.getSavedTeamDetails(teamId.toLongOrNull() ?: 0L)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
    ) {

        Image(
            painter = painterResource(R.drawable.teamprivew3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xAA07111F),
                            Color(0x3307111F),
                            Color(0xCC07111F)
                        )
                    )
                )
        )

        when {

            isLoading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = Color(0xFFFFD56A)
                    )
                }
            }

            teamDetails == null -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Team not found",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            else -> {

                val team = teamDetails!!

                val allPlayers = team.players ?: emptyList()

                val wk = allPlayers.filter {
                    it.playerRole.equals("WK-Batsman", ignoreCase = true)
                }

                val batting = allPlayers.filter {
                    it.playerRole.equals("Batsman", ignoreCase = true)
                }

                val allrounder = allPlayers.filter {
                    it.playerRole.equals("Bowling Allrounder", ignoreCase = true)
                            || it.playerRole.equals("All Rounder", ignoreCase = true)
                            || it.playerRole.equals("All-Rounder", ignoreCase = true)
                }

                val bowler = allPlayers.filter {
                    it.playerRole.equals("Bowler", ignoreCase = true)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        top = 6.dp,
                        bottom = 18.dp
                    )
                ) {

                    item {

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF07111F).copy(alpha = 0.76f)
                            ),
                            border = BorderStroke(
                                1.dp,
                                Color.White.copy(alpha = 0.10f)
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "Saved Team",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f)
                                    )

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(50.dp))
                                            .background(Color(0x1AFFB300))
                                            .padding(horizontal = 10.dp, vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Text(
                                            text = "${team.totalPlayers ?: 11} Players",
                                            style = TextStyle(
                                                color = Color(0xFFFFD56A),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${team.t1Name ?: ""} vs ${team.t2Name ?: ""}",
                                    style = TextStyle(
                                        color = Color(0xFFC5CDD8),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        lineHeight = 13.sp
                                    ),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color.White.copy(alpha = 0.07f))
                                            .padding(horizontal = 8.dp, vertical = 5.dp)
                                    ) {

                                        Text(
                                            text = "C: ${team.captainName ?: "-"}",
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0x1AFFB300))
                                            .padding(horizontal = 8.dp, vertical = 5.dp)
                                    ) {

                                        Text(
                                            text = "VC: ${team.viceCaptainName ?: "-"}",
                                            style = TextStyle(
                                                color = Color(0xFFFFD56A),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                    }

                    item {
                        SavedPlayerSection(
                            title = "Wicket Keepers",
                            players = wk,
                            t1name = team.t1Name ?: "",
                            t2name = team.t2Name ?: ""
                        )
                    }

                    item {
                        SavedPlayerSection(
                            title = "Batters",
                            players = batting,
                            t1name = team.t1Name ?: "",
                            t2name = team.t2Name ?: ""
                        )
                    }

                    item {
                        SavedPlayerSection(
                            title = "All Rounders",
                            players = allrounder,
                            t1name = team.t1Name ?: "",
                            t2name = team.t2Name ?: ""
                        )
                    }

                    item {
                        SavedPlayerSection(
                            title = "Bowlers",
                            players = bowler,
                            t1name = team.t1Name ?: "",
                            t2name = team.t2Name ?: ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SavedPlayerSection(
    title: String,
    players: List<SavedTeamPlayerResponse>,
    t1name: String,
    t2name: String
) {

    if (players.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xBB07111F))
                .padding(horizontal = 11.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 11.sp,
                    color = Color(0xFFFFD56A),
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        val chunked = players.chunked(4)

        chunked.forEach { rowItems ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {

                rowItems.forEach { player ->

                    SavedPlayerCard(
                        player = player,
                        team1 = t1name,
                        team2 = t2name
                    )
                }
            }
        }
    }
}

@Composable
fun SavedPlayerCard(
    player: SavedTeamPlayerResponse,
    team1: String,
    team2: String
) {

    val playerTeam = player.playerTeamName ?: ""

    val isFromTeam1 = team1.contains(playerTeam, ignoreCase = true)

    val textColor = if (isFromTeam1) Color.White else Color(0xFF07111F)
    val bgColor = if (isFromTeam1) Color(0xDD07111F) else Color(0xEEFFFFFF)

    Column(
        modifier = Modifier
            .width(74.dp)
            .padding(horizontal = 2.dp, vertical = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(50.dp),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.20f)),
                contentAlignment = Alignment.Center
            ) {

                GlideImage(
                    imageModel = {
                        if (
                            player.playerImg.isNullOrBlank()
                            || player.playerImg == "https://h.cricapi.com/img/icon512.png"
                        ) {
                            R.drawable.user
                        } else {
                            player.playerImg
                        }
                    },
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.size(14.dp),
                            color = Color(0xFFFFD56A)
                        )
                    },
                    failure = {
                        Image(
                            painter = painterResource(R.drawable.user),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                )
            }

            if (player.captain == true || player.viceCaptain == true) {

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(
                            if (player.captain == true) Color(0xFFFFB300)
                            else Color(0xFF00C853)
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {

                    Text(
                        text = if (player.captain == true) "C" else "VC",
                        style = TextStyle(
                            color = Color(0xFF07111F),
                            fontSize = 7.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = player.playerName ?: "",
            color = textColor,
            fontSize = 8.5.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 9.5.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(bgColor)
                .padding(horizontal = 3.dp, vertical = 2.dp)
        )
    }
}