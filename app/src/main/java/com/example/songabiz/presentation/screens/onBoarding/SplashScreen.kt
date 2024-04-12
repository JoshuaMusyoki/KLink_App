package com.example.songabiz.presentation.screens.onBoarding

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.songabiz.R
import com.example.songabiz.data.users.StoreUserData
import com.example.songabiz.presentation.screens.auth.AuthenticationState
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, alpha: Float = 0.5F){
    val context = LocalContext.current
    val dataStore = StoreUserData(context)
    val isSignedIn = dataStore.getLogin.collectAsState(initial = "")

    val scale = remember {
        Animatable(initialValue = 0f)
    }
    AuthenticationState()
    LaunchedEffect(key1 = true, block ={
        scale.animateTo(targetValue = 0.8f, animationSpec = tween(durationMillis = 700,
            easing = {OvershootInterpolator(2f).getInterpolation(it)})
        )
        delay(3000L)
        if(isSignedIn.value!! =="true"){
            navController.navigate("home_page_screen")
        }
        else {
            navController.navigate("authentication")
        }
    })

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
        Image(painter = painterResource(id = R.drawable.safeboda), contentDescription = "Safe Boda",
        contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxSize()
        )
    }
    Surface(
        color = Color.Black, modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            contentAlignment = Alignment.Center,
           modifier = Modifier.fillMaxSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.songawhite) ,
                    contentDescription = "Safe Boda",
                    modifier = Modifier.scale(scale.value)
                )
            }
        }
    }
}