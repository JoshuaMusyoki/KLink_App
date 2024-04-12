package com.example.songabiz.presentation.screens.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.songabiz.R
import com.example.songabiz.presentation.theme.GreenPrimary
import io.ktor.utils.io.tryCopyException

@Composable
fun Requirement(
    message: String,
    sastified: Boolean
){
    val tint = if (sastified){
        GreenPrimary
    } else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

    val requirementStatus = if(sastified){
        stringResource(id = R.string.password_requirement_satisfied, message)
    } else {
        stringResource(id = R.string.password_requirement_needed, message)
    }
    Row (
        modifier = Modifier
            .padding(6.dp)
            .semantics(mergeDescendants = true) {
                text = AnnotatedString(requirementStatus)
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.clearAndSetSemantics {  },
            text = message,
            fontSize = 12.sp,
            color = tint
        )

    }
}