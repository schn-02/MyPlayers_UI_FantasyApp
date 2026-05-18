package com.example.myplayer.SampleLayout

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myplayer.R
import com.example.myplayer.UpperNavigationView.UpperNavigationView
import com.example.myplayer.UpperNavigationView.viewModel.PlayersViewmodel
import com.example.myplayer.UpperNavigationView.viewModel.TrackViewModel
import com.skydoves.landscapist.glide.GlideImage
import java.net.URLEncoder

@Composable
fun ChoosePlayerSampleLayout(
    matchID: String,
    navController: NavController,
    t1Pic: String,
    t2Pic: String,
    time: Long,
    t1shortName: String,
    t2shortName: String,
    t1Name: String,
    t2name: String,
    trackViewmodel: TrackViewModel = viewModel()
) {

    val viewModel: PlayersViewmodel = viewModel()

    val countMap by trackViewmodel.selectedCountPerTeam
    val totalPlayerCount = trackViewmodel.selectedPlayerID.value.size

    Log.d("TIMENOTEUUPPER", "TeamNext: $time")

    LaunchedEffect(Unit) {
        viewModel.loadAllCategories(matchID)
    }

    val fullSquadData by viewModel.squad.collectAsState()

    LaunchedEffect(fullSquadData) {
        trackViewmodel.UpdateSquadList(fullSquadData)
        Log.d("completeSquadList", "Wicket: $fullSquadData")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.TopStart)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF00C853).copy(alpha = 0.16f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFB300).copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            TopBar(time)

            TeamView(
                matchID,
                navController,
                t1Pic,
                t2Pic,
                t1shortName,
                t2shortName,
                t1Name,
                t2name,
                time,
                trackViewmodel,
                countMap,
                totalPlayerCount
            )
        }
    }
}

@Composable
fun TopBar(time: Long) {

    Log.d("TimeChoose", "TopBar: $time")

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
                    painter = painterResource(R.drawable.eye),
                    contentDescription = null,
                    modifier = Modifier.size(19.dp)
                )
            }
        }
    }
}

@Composable
fun TeamView(
    matchID: String,
    navController: NavController,
    t1Pic: String,
    t2Pic: String,
    t1shortName: String,
    t2shortName: String,
    t1Name: String,
    t2name: String,
    time: Long,
    trackViewmodel: TrackViewModel,
    countMap: Map<String, Int>,
    totalPlayerCount: Int,
) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 2.dp)
            .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
        shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF101B2E),
                            Color(0xFF07111F)
                        )
                    )
                )
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.060f)
                ),
                border = BorderStroke(
                    1.dp,
                    Color.White.copy(alpha = 0.08f)
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
                            text = "Create Your Team",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0x1AFFB300))
                                .padding(horizontal = 9.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$totalPlayerCount/11",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color(0xFFFFD56A),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Max 7 players from one team",
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = Color(0xFF9AA5B5),
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    val selectedCountTeam1 = countMap[t1shortName] ?: 0
                    val selectedCountTeam2 = countMap[t2shortName] ?: 0

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            GlideImage(
                                imageModel = {
                                    if (t1Pic.isNotEmpty()) {
                                        t1Pic
                                    } else {
                                        R.drawable.cricket
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
                                        painter = painterResource(R.drawable.cricket),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.10f))
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = t1shortName,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    textAlign = TextAlign.Center
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "$selectedCountTeam1 selected",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    color = Color(0xFF9AA5B5),
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }

                        Column(
                            modifier = Modifier.width(82.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Players",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    color = Color(0xFF9AA5B5),
                                    fontWeight = FontWeight.SemiBold
                                )
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "$totalPlayerCount / 11",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    textAlign = TextAlign.Center
                                ),
                                maxLines = 1
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.White.copy(alpha = 0.08f))
                                    .padding(horizontal = 9.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "VS",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            GlideImage(
                                imageModel = {
                                    if (t2Pic.isNotEmpty()) {
                                        t2Pic
                                    } else {
                                        R.drawable.cricket
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
                                        painter = painterResource(R.drawable.cricket),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.10f))
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = t2shortName,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    textAlign = TextAlign.Center
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "$selectedCountTeam2 selected",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    color = Color(0xFF9AA5B5),
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(7.dp))

                    PlayerSelectionBar(totalPlayerCount)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f),
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

            UpperNavigationView(
                matchID,
                trackViewmodel,
                t1shortName,
                t2shortName,
                navController,
                t1Name,
                t2name,
                time,
                t1Pic,
                t2Pic
            )
        }
    }
}

@Composable
fun PlayerSelectionBar(selectedPlayer: Int) {

    val totalPlayers = 11

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        for (i in 0 until totalPlayers) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(7.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(
                        if (i < selectedPlayer) {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF00C853),
                                    Color(0xFFFFB300)
                                )
                            )
                        } else {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.16f),
                                    Color.White.copy(alpha = 0.08f)
                                )
                            )
                        }
                    )
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.08f),
                        RoundedCornerShape(100.dp)
                    )
            )
        }
    }
}

@Composable
fun MatchInfoDetails() {
    // Cricket Venue removed to give more space to player list.
}

@Composable
fun PreviewAndNext(
    navController: NavController,
    viewModel: TrackViewModel,
    t1Name: String,
    t2Name: String
) {

    Column {

        Card(
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {

            Button(
                onClick = {
                    navController.navigate("previewMyteam/$t1Name/$t2Name")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues()
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFFB300),
                                    Color(0xFFFFD56A)
                                )
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Preview",
                            color = Color(0xFF07111F),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.right),
                            contentDescription = "Preview Icon",
                            tint = Color(0xFF07111F),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeamNext(
    navController: NavController,
    viewModel: TrackViewModel,
    t1Name: String?,
    t2Name: String?,
    time: Long?,
    matchID: String?,
    t1shortName: String?,
    t2shortName: String?,
    t1Pic: String?,
    t2Pic: String?
) {

    val isNavigationSafe = !matchID.isNullOrBlank()

    val route = remember(
        matchID, t1Name, t2Name, t1shortName, t2shortName, t1Pic, t2Pic
    ) {
        if (isNavigationSafe) {
            val encodedMatchID = URLEncoder.encode(matchID!!, "UTF-8")
            val encodedT1Name = URLEncoder.encode(t1Name.orEmpty(), "UTF-8")
            val encodedT2Name = URLEncoder.encode(t2Name.orEmpty(), "UTF-8")
            val encodedT1Short = URLEncoder.encode(t1shortName.orEmpty(), "UTF-8")
            val encodedT2Short = URLEncoder.encode(t2shortName.orEmpty(), "UTF-8")
            val encodedT1Pic = URLEncoder.encode(t1Pic.orEmpty(), "UTF-8")
            val encodedT2Pic = URLEncoder.encode(t2Pic.orEmpty(), "UTF-8")

            "MyteamCVC/$encodedMatchID/$encodedT1Name/$encodedT2Name/$encodedT1Short/$encodedT2Short/$encodedT1Pic/$encodedT2Pic"
        } else null
    }

    Column {

        Card(
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {

            Button(
                onClick = {
                    route?.let {
                        navController.navigate(it)
                    }
                },
                enabled = isNavigationSafe,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.White.copy(alpha = 0.10f)
                ),
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues()
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF00C853),
                                    Color(0xFF008D43)
                                )
                            )
                        )
                        .padding(horizontal = 18.dp, vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Next",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.right),
                            contentDescription = "Preview Icon",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}