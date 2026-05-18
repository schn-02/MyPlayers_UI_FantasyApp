package com.example.myplayer.BottomNavigationView

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myplayer.BottomNavigationView.viewmodel.HomeViewModel
import com.example.myplayer.Model.UserDetialsModel
import com.example.myplayer.R
import com.example.myplayer.SampleLayout.HomeLayout
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {

    val viewmodel: HomeViewModel = viewModel()
    val matches by viewmodel.match.collectAsState()
    val isRefreshing by viewmodel.isRefreshing.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            viewmodel.refreshMatches()
        }
    }

    LaunchedEffect(isRefreshing) {
        if (!isRefreshing) {
            pullToRefreshState.endRefresh()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
    ) {

        TopBar(viewmodel)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF07111F))
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {

            Box(
                modifier = Modifier
                    .size(360.dp)
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
                    .size(320.dp)
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

            if (matches.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularProgressIndicator(
                            color = Color(0xFFFFB300),
                            trackColor = Color.White.copy(alpha = 0.10f)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Loading matches...",
                            style = TextStyle(
                                color = Color(0xFFC5CDD8),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 14.dp,
                        end = 14.dp,
                        top = 14.dp,
                        bottom = 90.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 2.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column {

                                    Text(
                                        text = "Upcoming Matches",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(3.dp))

                                    Text(
                                        text = "Choose a match and create your best XI",
                                        style = TextStyle(
                                            color = Color(0xFF9AA5B5),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(Color(0x1AFFB300))
                                        .padding(horizontal = 12.dp, vertical = 7.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(
                                        text = "${matches.size} Live",
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

                    items(matches) { item ->

                        val t1Pic = if (item.team1Pic.isNullOrBlank()) "null" else item.team1Pic
                        val t2Pic = if (item.team2Pic.isNullOrBlank()) "null" else item.team2Pic

                        val timeStamp = if (item.timeMilliSeconds == 0L) 0L else item.timeMilliSeconds

                        Log.d("XCXCXC", "TeamNext: ${timeStamp}")

                        val t1Short = if (item.team1ShortName.isNullOrBlank()) "null" else item.team1ShortName
                        val t2Short = if (item.team2ShortName.isNullOrBlank()) "null" else item.team2ShortName

                        val team1Name = if (item.team1.isNullOrBlank()) "null" else item.team1
                        val team2Name = if (item.team2.isNullOrBlank()) "null" else item.team2

                        val encodedT1Pic = URLEncoder.encode(t1Pic, "UTF-8")
                        val encodedT2Pic = URLEncoder.encode(t2Pic, "UTF-8")
                        val encodedT1Short = URLEncoder.encode(t1Short, "UTF-8")
                        val encodedT2Short = URLEncoder.encode(t2Short, "UTF-8")
                        val encodedteam1name = URLEncoder.encode(team1Name, "UTF-8")
                        val encodedteam2name = URLEncoder.encode(team2Name, "UTF-8")

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    Log.d("TIMENOTEUU", "TeamNext: ${item.timeMilliSeconds}")

                                    navController.navigate(
                                        "selectPlayers/${item.matchID}/$encodedT1Pic/$encodedT2Pic/$timeStamp/$encodedT1Short/$encodedT2Short/$encodedteam1name/$encodedteam2name"
                                    )
                                }
                        ) {

                            HomeLayout(item)
                        }
                    }
                }
            }

            PullToRefreshContainer(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer {
                        val scale = if (pullToRefreshState.isRefreshing) 1f else 0f
                        scaleX = scale
                        scaleY = scale
                    },
                state = pullToRefreshState,
            )
        }
    }
}

@Composable
fun TopBar(viewmodel: HomeViewModel) {

    var context = LocalContext.current

    LaunchedEffect(true) {
        viewmodel.getUserDetails()
    }

    val userDetails: UserDetialsModel = viewmodel.details.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF07111F))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(118.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF00A344),
                            Color(0xFF07111F)
                        )
                    )
                )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.085f)
            ),
            border = BorderStroke(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.10f)
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.95f))
                        .padding(5.dp)
                )

                Spacer(modifier = Modifier.width(11.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Hello,",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Text(
                        text = if (userDetails.name.isBlank()) "Champion" else userDetails.name,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        maxLines = 1
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0x15111111))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.wallet),
                        contentDescription = "Wallet",
                        tint = Color(0xFFFFD56A),
                        modifier = Modifier.size(21.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "₹${
                            if (userDetails.walletBalance.isEmpty()) {
                                "0"
                            } else {
                                userDetails.walletBalance
                            }
                        }",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.10f)),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "Notifications",
                        tint = Color(0xFFFFD56A),
                        modifier = Modifier.size(21.dp)
                    )
                }
            }
        }
    }
}