package com.example.myplayer.Authentication

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myplayer.R

@Composable
fun EnterDetailsLogin(
    number: String,
    navController: NavHostController
) {

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val authViewmodel: AuthViewmodel = viewModel()
    val uiState by authViewmodel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
    ) {

        Box(
            modifier = Modifier
                .size(340.dp)
                .offset(x = (-130).dp, y = (-90).dp)
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.065f)
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.09f)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {

                            Text(
                                text = "Hi Champion",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Complete your player profile",
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
                                text = "NEW",
                                style = TextStyle(
                                    color = Color(0xFFFFD56A),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .size(82.dp)
                            .clip(RoundedCornerShape(26.dp))
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
                                .size(62.dp)
                                .clip(CircleShape)
                                .background(Color(0x26000000)),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "11",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Fill Your Details",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = "This helps us personalize your fantasy experience",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    TextFieldssss(
                        name = name,
                        email = email,
                        onNameChange = {
                            name = it
                        },
                        onEmailChange = {
                            email = it
                        }
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    enter(
                        isLoading = isLoading,
                        onLoginClick = {

                            if (name.trim().isEmpty()) {

                                Toast.makeText(
                                    context,
                                    "Please enter your name",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {

                                isLoading = true

                                authViewmodel.SaveEnterDetails(
                                    name.trim(),
                                    email.trim(),
                                    number
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color.White.copy(alpha = 0.10f)
                        )

                        Text(
                            text = "  fair play profile  ",
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

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Mobile verified: +91 $number",
                        style = TextStyle(
                            color = Color(0xFF727C8B),
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }

        if (isLoading) {
            FullScreenProfileLottieLoader()
        }
    }

    LaunchedEffect(uiState) {

        when (val state = uiState) {

            is AuthUiState.ProfileCreated -> {
                isLoading = false

                navController.navigate("main") {
                    popUpTo("notUser/{mobile}") {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            is AuthUiState.Error -> {
                isLoading = false

                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> {
                // Idle or loading
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldssss(
    name: String,
    email: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF101B2E).copy(alpha = 0.94f)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.White.copy(alpha = 0.10f)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Player Details",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(13.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {

                    if (it.length <= 17) {
                        onNameChange(it)
                    }
                },
                label = {
                    Text(
                        text = "Enter your Name",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
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

                    focusedLabelColor = Color(0xFFFFB300),
                    unfocusedLabelColor = Color(0xFF8A95A6),

                    cursorColor = Color(0xFF00C853)
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = {
                    Text(
                        text = "Enter your Email ID (Optional)",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
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

                    focusedLabelColor = Color(0xFFFFB300),
                    unfocusedLabelColor = Color(0xFF8A95A6),

                    cursorColor = Color(0xFF00C853)
                )
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "Email is optional, you can add it later",
                style = TextStyle(
                    color = Color(0xFF8893A3),
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun enter(
    isLoading: Boolean = false,
    onLoginClick: () -> Unit
) {

    Button(
        onClick = onLoginClick,
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
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

            Text(
                text = if (isLoading) "Please wait..." else "Save",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Composable
fun FullScreenProfileLottieLoader() {

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
                    text = "Creating profile...",
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