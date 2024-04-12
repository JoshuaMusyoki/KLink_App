package com.example.songabiz.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.songabiz.R
import com.example.songabiz.data.users.StoreUserData
import com.example.songabiz.presentation.components.forms.AuthenticationForm
import com.example.songabiz.presentation.theme.GreenPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationContent(
    modifier:Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
    navController: NavController,
    alpha: Float = 0.5f,
){
    if (authenticationState.isSignedIn){
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val userDataStore =  StoreUserData(context)
        
        LaunchedEffect(Unit) {
            while (true){
                delay(500)
                scope.launch {
//                    userDataStore.saveLogin(name = "true")
//                    authenticationState.fname?.let { userDataStore.saveFName(name = it) }
                    authenticationState.fname?.let { userDataStore.saveFirstName(name = it) }
                    authenticationState.sname?.let { userDataStore.saveLastName(name = it)  }
                    authenticationState.phone?.let { userDataStore.savePhone(name = it) }
                    authenticationState.email?.let {  userDataStore.saveEmail(name = it)}
                    authenticationState.avatar?.let { userDataStore.saveAvatar(name = it) }
                    authenticationState.address?.let { userDataStore.saveAddress(name = it) }
                    authenticationState.id?.let {userDataStore.saveUserId(name = it)  }
                    authenticationState.sessionToken?.let { userDataStore.saveSessionToken(name = it)  }
                }
                navController.navigate("home_page_screen")
            }
        }
    }
    else {
        Scaffold (
            modifier = modifier
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black, GreenPrimary)
                        )
                    )
            ){}
            Surface(
                color = Color.Black, modifier = modifier
                    .fillMaxSize()
                    .alpha(alpha)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(shape = CircleShape)
                            .background(Color(0xFFD9D9D9), shape = CircleShape)
                            .border(
                                BorderStroke(
                                    1.dp,
                                    SolidColor(
                                        Color(0xFFD9D9D9),
                                    )
                                ),
                                shape = CircleShape
                            ),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.usergreen) ,
                            contentDescription = "User Placeholder",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(72.75.dp)
                                .height(67.5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AuthenticationForm(
                        modifier = Modifier.fillMaxSize(),
                        authenticationMode = authenticationState.authenticationMode,
                        fname = authenticationState.fname,
                        sname = authenticationState.sname,
                        phone = authenticationState.phone,
                        password = authenticationState.password,
                        completePasswordRequirements = authenticationState.passwordRequirements,
                        enableAuthentication = authenticationState.isFormValid(),

                        onFirstNameChanged = {
                            handleEvent(AuthenticationEvent.FirstNameChanged(it))
                        },

                        onSurNameChanged = {
                            handleEvent(AuthenticationEvent.LastNameChanged(it))
                        },

                        onPhoneChanged = {
                            handleEvent(AuthenticationEvent.PhoneChanged(it))
                        },

                        onPasswordChanged = {
                            handleEvent(AuthenticationEvent.PasswordChanged(it))
                        },

                        onAuthenticate = {
                            handleEvent(AuthenticationEvent.Authenticate)
                        },
                        onToggleMode = {
                            handleEvent(
                                AuthenticationEvent.ToggleAuthenticationMode
                            )
                        },
                        authenticationState = authenticationState
                    )

                    authenticationState.error?.let { error ->
                        AuthenticationErrorDialog(
                            error = error,
                            dismissError = {
                                handleEvent(
                                    AuthenticationEvent.ErrorDismissed
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}


