package com.example.myplayer.SampleLayout

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
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
fun SampelLayoutForCVC(
    player: MatchSquadModel,
    selectedRoles: Map<String, String>,
    onSelectRole: (String, String) -> Unit
) {

    Log.d("CVCC", "SampelLayoutForCVC: $selectedRoles")

    val isCaptain = selectedRoles["C"] == player.id
    val isViceCaptain = selectedRoles["VC"] == player.id

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isCaptain || isViceCaptain) {
                Color(0xFFFFD56A).copy(alpha = 0.75f)
            } else {
                Color.White.copy(alpha = 0.08f)
            }
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = if (isCaptain || isViceCaptain) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF102B24),
                                Color(0xFF07111F)
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
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.10f)),
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
                                modifier = Modifier.size(17.dp),
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
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(Modifier.width(11.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    player.name?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        player.country?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF9AA5B5)
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFD56A))
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        (if (player.role.equals("Bowling Allrounder")) {
                            "All-Rounder"
                        } else {
                            player.role
                        })?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFFFD56A)
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isCaptain) {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFFFB300),
                                        Color(0xFFFFD56A)
                                    )
                                )
                            } else {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.10f),
                                        Color.White.copy(alpha = 0.06f)
                                    )
                                )
                            }
                        )
                        .border(
                            1.dp,
                            if (isCaptain) {
                                Color(0xFFFFD56A)
                            } else {
                                Color.White.copy(alpha = 0.10f)
                            },
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            player.id?.let { onSelectRole("C", it) }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(
                            if (isCaptain) {
                                R.drawable.captainactivated
                            } else {
                                R.drawable.captain
                            }
                        ),
                        contentDescription = "Captain",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(2.dp)
                    )
                }

                Spacer(Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isViceCaptain) {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF00C853),
                                        Color(0xFF008D43)
                                    )
                                )
                            } else {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.10f),
                                        Color.White.copy(alpha = 0.06f)
                                    )
                                )
                            }
                        )
                        .border(
                            1.dp,
                            if (isViceCaptain) {
                                Color(0xFF00C853)
                            } else {
                                Color.White.copy(alpha = 0.10f)
                            },
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            player.id?.let { onSelectRole("VC", it) }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(
                            if (isViceCaptain) {
                                R.drawable.vicecaptainactivated
                            } else {
                                R.drawable.vicecaptain
                            }
                        ),
                        contentScale = ContentScale.Fit,
                        contentDescription = "Vice Captain",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}