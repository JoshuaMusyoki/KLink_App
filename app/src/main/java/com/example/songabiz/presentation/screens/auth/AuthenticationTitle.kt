package com.example.songabiz.presentation.screens.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.songabiz.R
import com.example.songabiz.presentation.theme.inter

@Composable
fun AuthenticationTitle(
    authenticationMode: AuthenticationMode
) {
    Text(
        text = stringResource(
            if (authenticationMode == AuthenticationMode.SIGN_UP){
                (R.string.label_sign_in_to_account)
            } else{
                R.string.label_sign_up_for_account
            }
        ),
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )
}