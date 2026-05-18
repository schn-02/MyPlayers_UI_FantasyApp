package com.example.myplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.myplayer.Authentication.EnterDetailsLogin
import com.example.myplayer.Authentication.MobileOtp
import com.example.myplayer.Authentication.signin
import com.example.myplayer.BottomNavigationView.Home
import com.example.myplayer.BottomNavigationView.MyTeam
import com.example.myplayer.BottomNavigationView.Profile
import com.example.myplayer.BottomNavigationView.Upcoming
import com.example.myplayer.Model.BottomNavigationView
import com.example.myplayer.PreviewTeam.PreviewTeam
import com.example.myplayer.PreviewTeam.SavedTeamPreviewScreen
import com.example.myplayer.PreviewTeam.SelectCVC
import com.example.myplayer.SampleLayout.ChoosePlayerSampleLayout
import com.example.myplayer.UpperNavigationView.viewModel.TrackViewModel
import com.example.myplayer.ui.theme.MyPlayerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import java.net.URLDecoder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        setContent {

            MyPlayerTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {

                    composable("splash") {
                        SplashScreen(navController)
                    }

                    composable("signin") {
                        signin(navController)
                    }

                    composable("MobileOtp/{mobile}") { backStackEntry ->

                        val mobileNumber = backStackEntry.arguments?.getString("mobile") ?: ""

                        MobileOtp(
                            mobilenumber = mobileNumber,
                            navController = navController
                        )
                    }

                    composable("notUser/{mobile}") { backStackEntry ->

                        val number = backStackEntry.arguments?.getString("mobile") ?: ""

                        EnterDetailsLogin(
                            number = number,
                            navController = navController
                        )
                    }

                    composable("main") {
                        MainScreen(
                            navController2 = navController,
                            startTab = "Home"
                        )
                    }

                    composable("main/{startTab}") { backStackEntry ->

                        val startTab = backStackEntry.arguments?.getString("startTab") ?: "Home"

                        MainScreen(
                            navController2 = navController,
                            startTab = startTab
                        )
                    }

                    navigation(
                        route = "gameFlow",
                        startDestination = "selectPlayers/{matchId}/{t1Pic}/{t2Pic}/{timeStamp}/{t1shortName}/{t2shortName}/{t1name}/{t2name}"
                    ) {

                        composable("selectPlayers/{matchId}/{t1Pic}/{t2Pic}/{timeStamp}/{t1shortName}/{t2shortName}/{t1name}/{t2name}") { backStackEntry ->

                            val matchID = backStackEntry.arguments?.getString("matchId") ?: ""

                            val t1Pic = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1Pic") ?: "",
                                "UTF-8"
                            )

                            val t2Pic = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2Pic") ?: "",
                                "UTF-8"
                            )

                            val timeStamp = backStackEntry.arguments
                                ?.getString("timeStamp")
                                ?.toLongOrNull() ?: 0L

                            val t1shortName = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1shortName") ?: "",
                                "UTF-8"
                            )

                            val t2shortName = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2shortName") ?: "",
                                "UTF-8"
                            )

                            val t1Name = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1name") ?: "",
                                "UTF-8"
                            )

                            val t2Name = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2name") ?: "",
                                "UTF-8"
                            )

                            ChoosePlayerSampleLayout(
                                matchID,
                                navController,
                                t1Pic,
                                t2Pic,
                                timeStamp,
                                t1shortName,
                                t2shortName,
                                t1Name,
                                t2Name
                            )
                        }

                        composable("previewMyteam/{t1Name}/{t2Name}") { backStackEntry ->

                            val t1Name = backStackEntry.arguments?.getString("t1Name") ?: ""
                            val t2Name = backStackEntry.arguments?.getString("t2Name") ?: ""

                            val parentEntry = remember {
                                navController.getBackStackEntry(
                                    "selectPlayers/{matchId}/{t1Pic}/{t2Pic}/{timeStamp}/{t1shortName}/{t2shortName}/{t1name}/{t2name}"
                                )
                            }

                            val viewModel: TrackViewModel = viewModel(parentEntry)

                            PreviewTeam(
                                navController = navController,
                                viewModel = viewModel,
                                t1name = t1Name,
                                t2name = t2Name
                            )
                        }

                        composable("MyteamCVC/{matchID}/{t1name}/{t2name}/{t1shortName}/{t2shortName}/{t1Pic}/{t2Pic}") { backStackEntry ->

                            val matchid = backStackEntry.arguments?.getString("matchID") ?: ""

                            val t1Pic = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1Pic") ?: "",
                                "UTF-8"
                            )

                            val t2Pic = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2Pic") ?: "",
                                "UTF-8"
                            )

                            val t1shortName = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1shortName") ?: "",
                                "UTF-8"
                            )

                            val t2shortName = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2shortName") ?: "",
                                "UTF-8"
                            )

                            val t1Name = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t1name") ?: "",
                                "UTF-8"
                            )

                            val t2Name = URLDecoder.decode(
                                backStackEntry.arguments?.getString("t2name") ?: "",
                                "UTF-8"
                            )

                            val parentEntry = remember {
                                navController.getBackStackEntry(
                                    "selectPlayers/{matchId}/{t1Pic}/{t2Pic}/{timeStamp}/{t1shortName}/{t2shortName}/{t1name}/{t2name}"
                                )
                            }

                            val viewmodel: TrackViewModel = viewModel(parentEntry)

                            SelectCVC(
                                navController,
                                viewmodel,
                                matchid,
                                t1Name,
                                t2Name,
                                t1shortName,
                                t2shortName,
                                t1Pic,
                                t2Pic
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController2: NavController,
    startTab: String = "Home"
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF07111F),
            darkIcons = false
        )
    }

    Column(
        modifier = Modifier.background(color = Color(0xFF07111F))
    ) {

        val navController = rememberNavController()

        val item = listOf(
            BottomNavigationView(
                route = "Home",
                icon = R.drawable.cricket,
                label = "Home"
            ),
            BottomNavigationView(
                route = "Upcoming",
                icon = R.drawable.upcoming,
                label = "Upcoming"
            ),
            BottomNavigationView(
                route = "MyTeam",
                icon = R.drawable.team,
                label = "MyTeam"
            ),
            BottomNavigationView(
                route = "Profile",
                icon = R.drawable.profile,
                label = "Profile"
            )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .clip(RoundedCornerShape(30.dp)),
            shape = RoundedCornerShape(30.dp)
        ) {

            Scaffold(
                bottomBar = {

                    NavigationBar(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 30.dp,
                                    bottomEnd = 30.dp
                                )
                            )
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF101B2E),
                                        Color(0xFF07111F)
                                    )
                                )
                            ),
                        containerColor = Color.Transparent
                    ) {

                        val currentState =
                            navController.currentBackStackEntryAsState().value?.destination?.route

                        item.forEach { item ->

                            NavigationBarItem(
                                icon = {
                                    Image(
                                        painter = painterResource(item.icon),
                                        contentDescription = item.label,
                                        modifier = Modifier.size(28.dp)
                                    )
                                },
                                selected = currentState == item.route,
                                label = {
                                    Text(
                                        text = item.label,
                                        style = TextStyle(
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                    )
                                },
                                onClick = {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            ) { innerpadding ->

                NavHost(
                    navController = navController,
                    startDestination = startTab,
                    modifier = Modifier.padding(innerpadding)
                ) {

                    composable("Home") {
                        Home(navController2)
                    }

                    composable("MyTeam") {
                        MyTeam(navController = navController)
                    }

                    composable("savedTeamPreview/{teamId}") { backStackEntry ->

                        val teamId = backStackEntry.arguments?.getString("teamId") ?: ""

                        SavedTeamPreviewScreen(
                            teamId = teamId,
                            navController = navController
                        )
                    }

                    composable("Upcoming") {
                        Upcoming()
                    }

                    composable("Profile") {
                        Profile(
                            onLogoutClick = {
                                FirebaseAuth.getInstance().signOut()

                                navController2.navigate("signin") {
                                    popUpTo("main") {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}