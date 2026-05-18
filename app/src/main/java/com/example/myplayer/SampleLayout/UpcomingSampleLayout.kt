package com.example.myplayer.SampleLayout

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplayer.Model.HomeMatch
import com.example.myplayer.R
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun UpcomingSampleLayout(homeMatch: HomeMatch) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 4.dp),
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
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF101B2E),
                            Color(0xFF07111F)
                        )
                    )
                )
        ) {

            Box(
                modifier = Modifier
                    .size(145.dp)
                    .align(Alignment.TopStart)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF00C853).copy(alpha = 0.13f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .size(145.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFB300).copy(alpha = 0.11f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp, vertical = 12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = homeMatch.seriesName.toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x1AFFB300))
                            .padding(horizontal = 9.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Upcoming",
                            style = TextStyle(
                                color = Color(0xFFFFD56A),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.10f)),
                            contentAlignment = Alignment.Center
                        ) {

                            GlideImage(
                                imageModel = { homeMatch.team1Pic },
                                loading = {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Color(0xFFFFD56A)
                                    )
                                },
                                failure = {
                                    Image(
                                        painter = painterResource(R.drawable.cricket),
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        homeMatch.team1?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = FontFamily.SansSerif,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 14.sp
                                ),
                                maxLines = 2,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .width(98.dp)
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.White.copy(alpha = 0.08f))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "STARTS IN",
                                style = TextStyle(
                                    fontSize = 9.sp,
                                    color = Color(0xFF9AA5B5),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        CompositionLocalProvider(LocalContentColor provides Color(0xFFFFD56A)) {
                            homeMatch.timeMilliSeconds?.let {
                                MatchCountdown(it)
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        homeMatch.date?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF9AA5B5),
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    lineHeight = 12.sp
                                ),
                                maxLines = 2
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.10f)),
                            contentAlignment = Alignment.Center
                        ) {

                            GlideImage(
                                imageModel = { homeMatch.team2Pic },
                                loading = {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Color(0xFFFFD56A)
                                    )
                                },
                                failure = {
                                    Image(
                                        painter = painterResource(R.drawable.cricket),
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        homeMatch.team2?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = FontFamily.SansSerif,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 14.sp
                                ),
                                maxLines = 2,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MatchCountdown(matchTime: Long) {

    Log.d("TIME", "MatchCountdown: $matchTime")

    var remainingTime by remember { mutableStateOf(matchTime - System.currentTimeMillis()) }

    LaunchedEffect(key1 = matchTime) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime = matchTime - System.currentTimeMillis()
        }
    }

    val hours = (remainingTime / (1000 * 60 * 60)).toInt()
    val minutes = ((remainingTime / (1000 * 60)) % 60).toInt()
    val seconds = ((remainingTime / 1000) % 60).toInt()

    Text(
        text = if (remainingTime > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            "Started"
        },
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = LocalContentColor.current,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ),
        maxLines = 1
    )
}