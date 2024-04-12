package com.example.songabiz.presentation.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.songabiz.R
import com.example.songabiz.presentation.screens.auth.AuthenticationMode
import com.example.songabiz.presentation.screens.auth.AuthenticationState
import com.example.songabiz.presentation.theme.GreenPrimary

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit,
    authenticationState: AuthenticationState,
){
    val focusManager = LocalFocusManager.current
    Button(
        modifier = modifier,
        onClick = {
            onAuthenticate()
            focusManager.clearFocus()
        },
        enabled = enableAuthentication,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = GreenPrimary
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        if (authenticationState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp),
                color = Color(0xFF0AFF7E)
            )
        } else {
            Text(
                text = stringResource(
                    if (authenticationMode == AuthenticationMode.SIGN_IN){
                        (R.string.action_sign_in)
                    } else {
                        R.string.action_sign_up
                    }
                )
            )
        }
    }
}