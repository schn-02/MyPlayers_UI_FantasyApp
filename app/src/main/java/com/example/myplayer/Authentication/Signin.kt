package com.example.myplayer.Authentication

import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myplayer.R

@Composable
fun signin(navController: NavController) {

    val context = LocalContext.current

    var mobileNumber by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
    ) {

        Box(
            modifier = Modifier
                .size(340.dp)
                .offset(x = (-120).dp, y = (-90).dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF00C853).copy(alpha = 0.28f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(270.dp)
                .align(Alignment.TopEnd)
                .offset(x = 90.dp, y = 120.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFB300).copy(alpha = 0.22f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(460.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 150.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x3325D366),
                            Color(0x22FFB300),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x22000000),
                            Color(0x66030A12)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.085f),
                                Color.White.copy(alpha = 0.025f)
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF00C853),
                                        Color(0xFFFFB300)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .size(74.dp)
                                .clip(CircleShape)
                                .background(Color(0x26000000)),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(42.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        text = "MY PLAYERS",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 31.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.2.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = "Create your winning cricket team",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0x1AFFB300))
                            .padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFB300))
                        )

                        Spacer(modifier = Modifier.width(7.dp))

                        Text(
                            text = "Fantasy Cricket • Live Contests",
                            style = TextStyle(
                                color = Color(0xFFFFD56A),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFields(
                        mobileNumber = mobileNumber,
                        onMobileChange = {
                            mobileNumber = it
                        }
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    loginGoogle(
                        isLoading = isLoading,
                        onLoginClick = {

                            if (mobileNumber.trim().isEmpty()) {

                                Toast.makeText(
                                    context,
                                    "Please Provide Your Mobile Number",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else if (mobileNumber.length < 10) {

                                Toast.makeText(
                                    context,
                                    "Please Enter Valid Mobile Number",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {

                                isLoading = true

                                navController.navigate("MobileOtp/$mobileNumber")
                            }
                        }
                    )
                }
            }
        }

        if (isLoading) {
            FullScreenLottieLoader()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(
    mobileNumber: String,
    onMobileChange: (String) -> Unit,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF101B2E).copy(alpha = 0.94f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Mobile Number",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "+91",
                    style = TextStyle(
                        color = Color(0xFFFFB300),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(11.dp))

            OutlinedTextField(
                value = mobileNumber,
                onValueChange = {

                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                        onMobileChange(it)
                    }
                },
                placeholder = {
                    Text(
                        text = "Enter mobile number",
                        color = Color(0xFF7E8998),
                        fontSize = 14.sp
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                leadingIcon = {
                    Text(
                        text = "+91",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                shape = RoundedCornerShape(17.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF07111F),
                    unfocusedContainerColor = Color(0xFF07111F),

                    focusedBorderColor = Color(0xFFFFB300),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.07f),

                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    cursorColor = Color(0xFF00C853),

                    focusedLeadingIconColor = Color.White,
                    unfocusedLeadingIconColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "We will send OTP to verify your number",
                style = TextStyle(
                    color = Color(0xFF8893A3),
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun loginGoogle(
    isLoading: Boolean = false,
    onLoginClick: () -> Unit
) {

    Column {

        Button(
            onClick = onLoginClick,
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            contentPadding = PaddingValues()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = if (isLoading) {
                                listOf(
                                    Color(0xFF0D2A22),
                                    Color(0xFF07111F)
                                )
                            } else {
                                listOf(
                                    Color(0xFF00C853),
                                    Color(0xFF008D43)
                                )
                            }
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                if (isLoading) {

                    Text(
                        text = "Please wait...",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                } else {

                    Text(
                        text = "Continue",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(21.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.White.copy(alpha = 0.10f)
            )

            Text(
                text = "  OR  ",
                style = TextStyle(
                    color = Color(0xFF7F8897),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.White.copy(alpha = 0.10f)
            )
        }

        Spacer(modifier = Modifier.height(21.dp))

        OutlinedButton(
            onClick = {},
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(
                1.dp,
                Color.White.copy(alpha = 0.10f)
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White.copy(alpha = 0.055f)
            )
        ) {

            Image(
                painter = painterResource(R.drawable.google),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Continue with Google",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "By continuing, you agree to fair play rules",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color(0xFF727C8B),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun FullScreenLottieLoader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.bowlingloading)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.58f)),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier.size(180.dp),
            shape = RoundedCornerShape(34.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF101B2E)
            ),
            border = BorderStroke(
                1.dp,
                Color.White.copy(alpha = 0.12f)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LottieAnimation(
                    composition = composition,
                    progress = {
                        progress
                    },
                    modifier = Modifier.size(105.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Please wait...",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}