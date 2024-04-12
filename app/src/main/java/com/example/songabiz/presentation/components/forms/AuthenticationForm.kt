package com.example.songabiz.presentation.components.forms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.example.songabiz.presentation.components.buttons.AuthenticationButton
import com.example.songabiz.presentation.components.inputs.auth.FirstNameInput
import com.example.songabiz.presentation.components.inputs.auth.PasswordInput
import com.example.songabiz.presentation.components.inputs.auth.SurnameInput
import com.example.songabiz.presentation.screens.auth.AuthenticationMode
import com.example.songabiz.presentation.screens.auth.AuthenticationState
import com.example.songabiz.presentation.screens.auth.AuthenticationTitle
import com.example.songabiz.presentation.screens.auth.PasswordRequirement
import com.example.songabiz.presentation.screens.auth.PasswordRequirements
import com.example.songabiz.presentation.screens.auth.ToggleAuthenticationMode

@Composable
fun AuthenticationForm(
    modifier: Modifier,
    fname: String?,
    sname: String?,
    phone: String?,
    password: String?,
    completePasswordRequirements: List<PasswordRequirements>,
    enableAuthentication: Boolean,
    authenticationMode: AuthenticationMode,
    onFirstNameChanged: (fname: String) -> Unit,
    onSurNameChanged: (sname: String) -> Unit,
    onPhoneChanged: (phone: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    onToggleMode: () -> Unit,
    authenticationState: AuthenticationState,
    ){
    remember { mutableStateOf("") }
    Column (
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AuthenticationTitle(
            authenticationMode = authenticationMode
        )
        Spacer(modifier = Modifier.height(25.dp))
        val passwordFocusRequester = FocusRequester()
        val surnameFocusRequester = FocusRequester()
        val phoneFocusRequester = FocusRequester()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        ){
            Column (
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AnimatedVisibility(
                    visible = authenticationMode == AuthenticationMode.SIGN_UP
                ) {
                    Row {
                        FirstNameInput(
                            modifier = modifier
                                .height(50.dp)
                                .width(170.dp),
                            fname = fname?:"",
                            onFirstNameChanged = onFirstNameChanged
                        ){
                            surnameFocusRequester.requestFocus()
                        }
                        SurnameInput(
                            modifier = Modifier
                                .focusRequester(surnameFocusRequester)
                                .height(50.dp)
                                .width(170.dp),
                            sname = sname?:"",
                            onSurnameChanged = onSurNameChanged
                        ){
                            phoneFocusRequester.requestFocus()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                PasswordInput(
                    modifier = modifier
                        .height(50.dp)
                        .focusRequester(passwordFocusRequester),
                    password = password?:"",
                    onPasswordChanged = onPasswordChanged,
                    onDoneClicked = onAuthenticate
                )
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = authenticationMode == AuthenticationMode.SIGN_UP
                ) {
                    PasswordRequirement(sastifiedRequirements = completePasswordRequirements)
                }
                Spacer(modifier = Modifier.height(10.dp))
                AuthenticationButton(
                    enableAuthentication = enableAuthentication,
                    authenticationMode = authenticationMode,
                    onAuthenticate = onAuthenticate,
                    modifier = Modifier.width(170.dp),
                    authenticationState = authenticationState
                )
                Spacer(modifier = Modifier.height(20.dp))
                ToggleAuthenticationMode(
                    authenticationMode = authenticationMode
                ){
                    onToggleMode()
                }
            }
        }
    }
}

