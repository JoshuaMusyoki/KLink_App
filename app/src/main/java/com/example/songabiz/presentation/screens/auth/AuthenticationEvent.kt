package com.example.songabiz.presentation.screens.auth

import kotlinx.coroutines.flow.MutableStateFlow

sealed class AuthenticationEvent {
    object ToggleAuthenticationMode: AuthenticationEvent()
    val uiState = MutableStateFlow(AuthenticationState())
     class FirstNameChanged(val fname: String):
             AuthenticationEvent()
    class LastNameChanged(val sname: String):
            AuthenticationEvent()
    class PhoneChanged(val phone: String):
            AuthenticationEvent()
    class EmailChanged(val email:String):
            AuthenticationEvent()
    class PasswordChanged(val password:String):
            AuthenticationEvent()
    object Authenticate: AuthenticationEvent()

    object ErrorDismissed: AuthenticationEvent()
}