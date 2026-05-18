package com.example.myplayer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myplayer.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val scale = remember { Animatable(0.78f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 850,
                easing = FastOutSlowInEasing
            )
        )

        delay(2200)

        val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null

        navController.navigate(
            if (isUserLoggedIn) "main" else "signin"
        ) {
            popUpTo("splash") {
                inclusive = true
            }
        }
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
                .size(390.dp)
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
                .size(350.dp)
                .align(Alignment.BottomEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFB300).copy(alpha = 0.16f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(235.dp)
                    .scale(scale.value),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(190.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF123528),
                                    Color(0xFF07111F)
                                )
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .size(175.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.06f))
                )

                Image(
                    painter = painterResource(R.drawable.cricketplayer),
                    contentDescription = "My Players Logo",
                    modifier = Modifier.size(165.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "My Players",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Create • Compete • Win",
                style = TextStyle(
                    color = Color(0xFFFFD56A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            )

            Spacer(modifier = Modifier.height(34.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = Color(0xFFFFD56A),
                trackColor = Color.White.copy(alpha = 0.10f),
                strokeWidth = 3.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Preparing fantasy arena...",
                style = TextStyle(
                    color = Color(0xFF9AA5B5),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}