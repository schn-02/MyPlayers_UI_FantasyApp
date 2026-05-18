package com.example.myplayer.Authentication

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myplayer.Animation.SiginAnimation
import com.example.myplayer.R
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun MobileOtp(
    mobilenumber: String,
    navController: NavController
) {

    val context = LocalContext.current
    val otpContext = LocalActivity.current as Activity

    var otpValue by remember { mutableStateOf("") }
    var verificationID by rememberSaveable { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val authviewmodel: AuthViewmodel = viewModel()
    val uiState by authviewmodel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        GenrateOTP(
            phoneNumber = mobilenumber,
            activity = otpContext,
            onCodeSent = { id ->
                verificationID = id
            },
            onVerificationCompleted = { credential ->
                isLoading = true
                authviewmodel.signinCredentials(credential)
            },
            onVerificationFailed = {
                isLoading = false

                Toast.makeText(
                    context,
                    "Verification Failed: ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

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
                .offset(x = 95.dp, y = 130.dp)
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

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "One step away from the match",
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
                                text = "OTP",
                                style = TextStyle(
                                    color = Color(0xFFFFD56A),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    SiginAnimation()

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        text = "Verify your Phone Number",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Code sent to +91 $mobilenumber",
                        style = TextStyle(
                            color = Color(0xFFC5CDD8),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    OtpInputBox(
                        otpLength = 6,
                        onOtpComplete = {
                            otpValue = it
                        }
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Enter the 6 digit verification code",
                        style = TextStyle(
                            color = Color(0xFF8893A3),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(),
                        onClick = {

                            if (otpValue.length == 6 && verificationID.isNotEmpty()) {

                                isLoading = true

                                val credential = PhoneAuthProvider.getCredential(
                                    verificationID,
                                    otpValue
                                )

                                authviewmodel.signinCredentials(credential)

                            } else if (verificationID.isEmpty()) {

                                Toast.makeText(
                                    context,
                                    "Please wait, OTP is still being sent",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {

                                Toast.makeText(
                                    context,
                                    "Please enter 6 digit OTP",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
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
                                text = if (isLoading) "Please wait..." else "Verify",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                color = Color.White
                            )
                        }
                    }

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
                            text = "  secure login  ",
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Didn't receive code? Try again in few seconds",
                        style = TextStyle(
                            color = Color(0xFF727C8B),
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }

        if (isLoading) {
            FullScreenOtpLottieLoader()
        }
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {

            is AuthUiState.UserExists -> {
                isLoading = false

                navController.navigate("main") {
                    popUpTo("MobileOtp/{mobile}") {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            is AuthUiState.NewUser -> {
                isLoading = false

                navController.navigate("notUser/$mobilenumber") {
                    popUpTo("MobileOtp/{mobile}") {
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

            is AuthUiState.OtpVerified -> {
                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                // Idle or Loading
            }
        }
    }
}

@Composable
fun OtpInputBox(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {

    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                focusRequester.requestFocus()
            },
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            repeat(otpLength) { index ->

                val char = otpValue.getOrNull(index)?.toString() ?: ""

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(43.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            brush = if (char.isNotEmpty()) {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF00C853),
                                        Color(0xFFFFB300)
                                    )
                                )
                            } else {
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF101B2E),
                                        Color(0xFF0B1525)
                                    )
                                )
                            }
                        )
                ) {

                    Text(
                        text = char,
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 19.sp
                        ),
                        color = if (char.isNotEmpty()) {
                            Color.White
                        } else {
                            Color.Transparent
                        }
                    )
                }
            }
        }

        BasicTextField(
            value = otpValue,
            onValueChange = { value ->

                if (value.length <= otpLength && value.all { ch -> ch.isDigit() }) {
                    otpValue = value
                    onOtpComplete(value)
                }
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { }
                .fillMaxWidth()
                .height(56.dp)
                .alpha(0f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}

@Composable
fun FullScreenOtpLottieLoader() {

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
                    text = "Verifying...",
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