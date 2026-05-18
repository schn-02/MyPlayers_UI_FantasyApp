package com.example.myplayer.BottomNavigationView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myplayer.Model.SavedTeamListResponse
import com.example.myplayer.UpperNavigationView.viewModel.MyTeamViewModel

@Composable
fun MyTeam(
    navController: NavController,
    myTeamViewModel: MyTeamViewModel = viewModel()
) {

    val myTeams by myTeamViewModel.myTeams.collectAsState()
    val isLoading by myTeamViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        myTeamViewModel.getMySavedTeams()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
    ) {

        Box(
            modifier = Modifier
                .size(330.dp)
                .align(Alignment.TopStart)
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFF00C853).copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            MyTeamTopBar(myTeams.size)

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

                myTeams.isEmpty() -> {
                    EmptyMyTeamState()
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 12.dp,
                            end = 12.dp,
                            top = 8.dp,
                            bottom = 90.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(myTeams) { team ->

                            SavedTeamCard(
                                team = team,
                                onClick = {
                                    navController.navigate("savedTeamPreview/${team.teamId}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyTeamTopBar(totalTeams: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.07f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "My Teams",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Text(
                    text = "Your saved fantasy teams",
                    style = TextStyle(
                        color = Color(0xFF9AA5B5),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0x1AFFB300))
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "$totalTeams Teams",
                    style = TextStyle(
                        color = Color(0xFFFFD56A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun SavedTeamCard(
    team: SavedTeamListResponse,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF101B2E),
                            Color(0xFF07111F)
                        )
                    )
                )
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${team.t1Name ?: ""} vs ${team.t2Name ?: ""}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x1A00C853))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "${team.totalPlayers ?: 11}/11",
                            style = TextStyle(
                                color = Color(0xFF64F0A0),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TeamImageCircle(
                        teamImage = team.t1Pic ?: "",
                        shortName = team.t1ShortName ?: "T1",
                        teamName = team.t1Name ?: "Team 1",
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "VS",
                        style = TextStyle(
                            color = Color(0xFFFFD56A),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                            .padding(horizontal = 13.dp, vertical = 6.dp)
                    )

                    TeamImageCircle(
                        teamImage = team.t2Pic ?: "",
                        shortName = team.t2ShortName ?: "T2",
                        teamName = team.t2Name ?: "Team 2",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(13.dp))

                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.08f),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(11.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Captain",
                            style = TextStyle(
                                color = Color(0xFF9AA5B5),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = team.captainName ?: "Not selected",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "Vice Captain",
                            style = TextStyle(
                                color = Color(0xFF9AA5B5),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = team.viceCaptainName ?: "Not selected",
                            style = TextStyle(
                                color = Color(0xFFFFD56A),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White.copy(alpha = 0.055f))
                        .padding(vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Tap to preview team",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TeamImageCircle(
    teamImage: String,
    shortName: String,
    teamName: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF00C853),
                            Color(0xFFFFB300)
                        )
                    )
                )
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color(0xFF07111F)),
                contentAlignment = Alignment.Center
            ) {

                if (teamImage.isNotBlank()) {

                    AsyncImage(
                        model = teamImage,
                        contentDescription = teamName,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    Text(
                        text = shortName.take(3).uppercase(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = teamName,
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun EmptyMyTeamState() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.07f)
            ),
            border = BorderStroke(
                1.dp,
                Color.White.copy(alpha = 0.10f)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "No teams created yet",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Create your fantasy team and it will appear here.",
                    style = TextStyle(
                        color = Color(0xFF9AA5B5),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                )
            }
        }
    }
}