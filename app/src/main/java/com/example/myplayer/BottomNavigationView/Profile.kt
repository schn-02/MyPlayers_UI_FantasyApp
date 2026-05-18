package com.example.myplayer.BottomNavigationView

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myplayer.UpperNavigationView.viewModel.ProfileViewModel

@Composable
fun Profile(
    profileViewModel: ProfileViewModel = viewModel(),
    onLogoutClick: () -> Unit = {}
) {

    val userDetails by profileViewModel.userDetails.collectAsState()
    val isLoading by profileViewModel.isLoading.collectAsState()
    val errorMessage by profileViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getUserDetails()
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
                .size(340.dp)
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
                .size(300.dp)
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

        when {

            isLoading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularProgressIndicator(
                            color = Color(0xFFFFD56A),
                            trackColor = Color.White.copy(alpha = 0.10f)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Loading profile...",
                            style = TextStyle(
                                color = Color(0xFFC5CDD8),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }

            errorMessage.isNotBlank() -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        shape = RoundedCornerShape(24.dp),
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
                                .padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Unable to load profile",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = errorMessage,
                                style = TextStyle(
                                    color = Color(0xFF9AA5B5),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }

            else -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProfileHeaderCard(
                        name = userDetails.name ?: "Player",
                        email = userDetails.useremail ?: "No email found"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    WalletCard(
                        walletBalance = userDetails.walletBalance ?: "0"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileInfoCard(
                        name = userDetails.name ?: "Not available",
                        email = userDetails.useremail ?: "Not available",
                        mobileNumber = userDetails.usermobileNumber ?: "Not available",
                        walletBalance = userDetails.walletBalance ?: "0"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    LogoutButton(
                        onLogoutClick = onLogoutClick
                    )

                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        }
    }
}

@Composable
fun ProfileHeaderCard(
    name: String,
    email: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.075f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
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

                Text(
                    text = getProfileInitial(name),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = name.ifBlank { "Player" },
                style = TextStyle(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = email.ifBlank { "No email found" },
                style = TextStyle(
                    color = Color(0xFFC5CDD8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun WalletCard(
    walletBalance: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFFFFD56A).copy(alpha = 0.22f)
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF102B24),
                            Color(0xFF07111F)
                        )
                    )
                )
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {

            Column {

                Text(
                    text = "Wallet Balance",
                    style = TextStyle(
                        color = Color(0xFF9AA5B5),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "₹${walletBalance.ifBlank { "0" }}",
                    style = TextStyle(
                        color = Color(0xFFFFD56A),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Available for joining contests",
                    style = TextStyle(
                        color = Color(0xFFC5CDD8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
fun ProfileInfoCard(
    name: String,
    email: String,
    mobileNumber: String,
    walletBalance: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.070f)
        ),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.10f)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            Text(
                text = "Account Details",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            ProfileInfoRow(
                label = "Username",
                value = name
            )

            ProfileDivider()

            ProfileInfoRow(
                label = "Email",
                value = email
            )

            ProfileDivider()

            ProfileInfoRow(
                label = "Mobile Number",
                value = mobileNumber
            )

            ProfileDivider()

            ProfileInfoRow(
                label = "Wallet Balance",
                value = "₹${walletBalance.ifBlank { "0" }}"
            )
        }
    }
}

@Composable
fun ProfileInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = label,
                style = TextStyle(
                    color = Color(0xFF9AA5B5),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = value.ifBlank { "Not available" },
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun LogoutButton(
    onLogoutClick: () -> Unit
) {

    Button(
        onClick = onLogoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE53935),
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 18.dp)
    ) {

        Text(
            text = "Logout",
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}

@Composable
fun ProfileDivider() {

    HorizontalDivider(
        color = Color.White.copy(alpha = 0.08f),
        thickness = 1.dp
    )
}

fun getProfileInitial(name: String): String {

    val cleanName = name.trim()

    if (cleanName.isBlank()) {
        return "P"
    }

    return cleanName.first().uppercase()
}