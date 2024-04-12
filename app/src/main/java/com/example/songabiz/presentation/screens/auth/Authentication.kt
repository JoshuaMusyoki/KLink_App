package com.example.songabiz.presentation.screens.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.songabiz.presentation.viewModels.AuthenticationViewModel

@Composable
fun Authentication(navController: NavController){
    val viewModel: AuthenticationViewModel = viewModel()
    MaterialTheme {
        AuthenticationContent(
            modifier = Modifier
                .fillMaxWidth(),
            authenticationState = viewModel.uiState.collectAsState().value,
            navController = navController,
            handleEvent = viewModel::handleEvent
        )
    }
}