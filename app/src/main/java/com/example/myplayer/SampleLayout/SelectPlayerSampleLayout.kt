package com.example.myplayer.SampleLayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplayer.Model.MatchSquadModel
import com.example.myplayer.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SelectPlayersSampleLayout(player: MatchSquadModel, isSelected: Boolean) {

    Column {
        PlayerCard(player = player, isSelected)

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun PlayerCard(player: MatchSquadModel, isSelected: Boolean) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) {
                Color(0xFFFFD56A).copy(alpha = 0.75f)
            } else {
                Color.White.copy(alpha = 0.08f)
            }
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = if (isSelected) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF00A344),
                                Color(0xFF101B2E)
                            )
                        )
                    } else {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF101B2E),
                                Color(0xFF07111F)
                            )
                        )
                    }
                )
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) {
                            Color.White.copy(alpha = 0.18f)
                        } else {
                            Color.White.copy(alpha = 0.10f)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                GlideImage(
                    imageModel = {
                        if (player.playerImg == "https://h.cricapi.com/img/icon512.png") {
                            R.drawable.user
                        } else {
                            player.playerImg
                        }
                    },
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color(0xFFFFD56A)
                        )
                    },
                    failure = {
                        Image(
                            painter = painterResource(R.drawable.user),
                            contentDescription = null,
                            modifier = Modifier.size(38.dp)
                        )
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(11.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                player.name?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(3.dp))

                player.teamName?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = if (isSelected) {
                                Color(0xFFFFD56A)
                            } else {
                                Color(0xFF9AA5B5)
                            },
                            fontWeight = FontWeight.SemiBold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(
                            if (isSelected) {
                                Color(0x22FFB300)
                            } else {
                                Color.White.copy(alpha = 0.06f)
                            }
                        )
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {

                    Text(
                        text = if (isSelected) "Selected" else "Tap to pick",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = if (isSelected) {
                                Color(0xFFFFD56A)
                            } else {
                                Color(0xFF9AA5B5)
                            },
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.10f)),
                contentAlignment = Alignment.Center
            ) {

                GlideImage(
                    imageModel = { player.img },
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
                        .clip(CircleShape)
                        .size(42.dp)
                )
            }
        }
    }
}