package com.example.songabiz.presentation.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun PasswordRequirement(
    modifier: Modifier = Modifier,
    sastifiedRequirements: List<PasswordRequirements>
){
    Column(
        modifier = modifier
    ) {
        PasswordRequirements.entries.forEach { requirement ->
            Requirement(
                message = stringResource(id = requirement.label),
                sastified = sastifiedRequirements.contains(requirement)
            )
        }
    }
}