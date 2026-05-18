package com.example.myplayer.PreviewTeam

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myplayer.Model.MatchSquadModel
import com.example.myplayer.R
import com.example.myplayer.SampleLayout.MatchCountdown
import com.example.myplayer.SampleLayout.SampelLayoutForCVC
import com.example.myplayer.UpperNavigationView.viewModel.TrackViewModel

@Composable
fun SelectCVC(
    navController: NavHostController,
    viewmodel: TrackViewModel,
    matchID: String,
    t1Name: String,
    t2Name: String,
    t1shortName: String,
    t2shortName: String,
    t1Pic: String,
    t2Pic: String
) {

    val players = viewmodel.getPlayersByIdForTeamPreview()
    Log.d("CHECKING", "SelectCVC: $players")

    val wk = players.wk
    val bat = players.bat
    val all = players.all
    val bowler = players.bowl

    val playerSections = listOf(
        "Wicket Keepers" to wk,
        "Batsmen" to bat,
        "All Rounders" to all,
        "Bowlers" to bowler
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Box(
            modifier = Modifier
                .size(330.dp)
                .align(Alignment.TopStart)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF00C853).copy(alpha = 0.18f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(290.dp)
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFB300).copy(alpha = 0.13f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TeamViewCVC()

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 4.dp,
                    bottom = 10.dp
                )
            ) {

                playerSections.forEach { (title, playersList) ->

                    if (playersList.isNotEmpty()) {

                        item {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = title,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(Color(0x1AFFB300))
                                        .padding(horizontal = 9.dp, vertical = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(
                                        text = "${playersList.size}",
                                        style = TextStyle(
                                            color = Color(0xFFFFD56A),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                HorizontalDivider(
                                    modifier = Modifier.weight(1f),
                                    thickness = 1.dp,
                                    color = Color.White.copy(alpha = 0.10f)
                                )
                            }
                        }

                        items(playersList) { player ->

                            SampelLayoutForCVC(
                                player = player,
                                selectedRoles = viewmodel.selectedRoles.value,
                                onSelectRole = viewmodel::onSelectRole
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {

                if (viewmodel.selectedRoles.value.size == 2) {

                    BottomBarCVCSave(
                        navController,
                        matchID,
                        viewmodel,
                        playerSections,
                        t1Name,
                        t2Name,
                        t1shortName,
                        t2shortName,
                        t1Pic,
                        t2Pic
                    )

                } else {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.060f)
                        ),
                        border = BorderStroke(
                            1.dp,
                            Color.White.copy(alpha = 0.08f)
                        )
                    ) {

                        Text(
                            text = "Select Captain and Vice Captain to continue",
                            style = TextStyle(
                                color = Color(0xFF9AA5B5),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 14.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarCVC(time: Long) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.075f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 12.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.10f))
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }

            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                CompositionLocalProvider(LocalContentColor provides Color(0xFFFFD56A)) {
                    MatchCountdown(time)
                }
            }

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.10f))
                    .align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.questionmark),
                    contentDescription = null,
                    modifier = Modifier.size(19.dp)
                )
            }
        }
    }
}

@Composable
fun TeamViewCVC() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.070f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Choose Captain & Vice Captain",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0x1AFFB300))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "C = 2x points",
                        style = TextStyle(
                            color = Color(0xFFFFD56A),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0x1A00C853))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "VC = 1.5x points",
                        style = TextStyle(
                            color = Color(0xFF64F0A0),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarCVCSave(
    navController: NavHostController,
    matchID: String,
    viewmodel: TrackViewModel,
    playerSections: List<Pair<String, List<MatchSquadModel>>>,
    t1Name: String,
    t2Name: String,
    t1shortName: String,
    t2shortName: String,
    t1Pic: String,
    t2Pic: String,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .height(54.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF00C853),
                        Color(0xFF008D43)
                    )
                )
            )
            .clickable {

                viewmodel.saveTeamToServer(
                    matchID = matchID,
                    t1Name = t1Name,
                    t2Name = t2Name,
                    t1shortName = t1shortName,
                    t2shortName = t2shortName,
                    t1Pic = t1Pic,
                    t2Pic = t2Pic,
                    onSuccess = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

                        navController.navigate("main/MyTeam") {
                            popUpTo("gameFlow") {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            }
    ) {

        Text(
            text = "Save Team",
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        Image(
            painter = painterResource(R.drawable.right),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        )
    }
}