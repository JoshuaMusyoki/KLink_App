package com.example.songabiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.songabiz.presentation.screens.app.user.home.HomeScreen
import com.example.songabiz.presentation.screens.auth.Authentication
import com.example.songabiz.presentation.screens.auth.SignUpScreen
import com.example.songabiz.presentation.screens.auth.login.LoginScreen
import com.example.songabiz.presentation.screens.onBoarding.SplashScreen
import com.example.songabiz.presentation.screens.onBoarding.WebsiteScreen
import kotlinx.coroutines.delay

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val isLoading = remember { mutableStateOf(true) }
    val isSplashScreenShown  = remember { mutableStateOf(true) }
    
    LaunchedEffect(key1 = isLoading.value) {
        delay(2000)
        isLoading.value = false
        isSplashScreenShown.value = true
    }
    
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen"){
            SplashScreen(navController = navController, 0.9f)
        }
        composable("home_screen"){
            HomeScreen(navController = navController, 0.75f)
        }
        composable("faqs_screen"){

        }
        composable("website_screen"){
            WebsiteScreen(myUrl = "https://songa.app")
        }
        composable("home_page_screen"){

        }
        composable("ride_history_screen"){

        }
        composable("invite_friends_screen"){

        }
        composable("online_support_screen"){

        }
        composable("settings_screen"){

        }
        composable("my_wallet_screen"){

        }
        composable("login_screen"){
            LoginScreen(navController = navController, alpha = 0.75f)
        }
        composable("signup_screen"){
            SignUpScreen(navController = navController, 0.75f)
        }
        composable("edit_profile_screen"){

        }
        composable("change_password_screen"){

        }
        composable("authentication"){
            Authentication(navController = navController )
        }
    }
}