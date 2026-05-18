package com.example.myplayer.PreviewTeam

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.myplayer.Model.MatchSquadModel
import com.example.myplayer.R
import com.example.myplayer.UpperNavigationView.viewModel.TrackViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PreviewTeam(
    navController: NavController,
    viewModel: TrackViewModel,
    t1name: String,
    t2name: String
) {

    Log.d("LODD", "PreviewTeam: ${viewModel.getPlayersByIdForTeamPreview()}")

    val wk = viewModel.getPlayersByIdForTeamPreview().wk
    val batting = viewModel.getPlayersByIdForTeamPreview().bat
    val bowler = viewModel.getPlayersByIdForTeamPreview().bowl
    val allrounder = viewModel.getPlayersByIdForTeamPreview().all

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF07111F).copy(alpha = 0.72f)
                ),
                border = BorderStroke(
                    1.dp,
                    Color.White.copy(alpha = 0.10f)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Team Preview",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.ExtraBold
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0x1AFFB300))
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "11 Players",
                                style = TextStyle(
                                    color = Color(0xFFFFD56A),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "$t1name vs $t2name",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 15.sp
                        ),
                        maxLines = 3
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            PlayerSection("Wicket Keepers", wk, t1name, t2name)
            PlayerSection("Batters", batting, t1name, t2name)
            PlayerSection("All Rounders", allrounder, t1name, t2name)
            PlayerSection("Bowlers", bowler, t1name, t2name)
        }
    }
}

@Composable
fun PlayerSection(
    title: String,
    players: List<MatchSquadModel>,
    t1shortname: String,
    t2shortname: String
) {

    if (players.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xBB07111F))
                .padding(horizontal = 13.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFFFD56A),
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        val chunked = players.chunked(4)

        chunked.forEach { rowItems ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {

                rowItems.forEach { player ->

                    SampleLayoutPlayerCard(
                        player = player,
                        t1shortname,
                        t2shortname
                    )
                }
            }
        }
    }
}

@Composable
fun SampleLayoutPlayerCard(
    player: MatchSquadModel,
    team1: String,
    team2: String
) {

    Log.d("KON", "PreviewTeam: ${player.country}")

    val isFromTeam1 = team1.contains(player.country ?: "", ignoreCase = true)

    val textColor = if (isFromTeam1) Color.White else Color(0xFF07111F)
    val bgColor = if (isFromTeam1) Color(0xDD07111F) else Color(0xEEFFFFFF)

    Column(
        modifier = Modifier
            .width(78.dp)
            .padding(horizontal = 3.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.20f)),
            contentAlignment = Alignment.Center
        ) {

            GlideImage(
                imageModel = {
                    if (player.playerImg.equals("https://h.cricapi.com/img/icon512.png")) {
                        R.drawable.user
                    } else {
                        player.playerImg
                    }
                },
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = Color(0xFFFFD56A)
                    )
                },
                failure = {
                    Image(
                        painter = painterResource(R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                },
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        player.name?.let {
            Text(
                text = it,
                color = textColor,
                fontSize = 9.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(bgColor)
                    .padding(horizontal = 3.dp, vertical = 2.dp)
            )
        }
    }
}