package com.example.songabiz.presentation.components.inputs.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.songabiz.R
import com.example.songabiz.presentation.theme.GreenPrimary
import com.example.songabiz.presentation.theme.ibmplexsanshebrew

@Composable
fun FirstNameInput(
    modifier: Modifier = Modifier,
    fname:String?,
    onFirstNameChanged: (fname: String) -> Unit,
    onNextClicked: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.label_firstname),
            fontFamily = ibmplexsanshebrew,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedTextField(
            value = fname ?: "",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextClicked()
                }
            ),
            onValueChange = { fname ->
                onFirstNameChanged(fname)
            },
            placeholder = { Text(stringResource(id = R.string.label_firstname),
                fontSize = 14.sp,
                fontFamily = ibmplexsanshebrew
                )},
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Unspecified,
                cursorColor = GreenPrimary,
                focusedPlaceholderColor = Color(0xFF4D4D4D)
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = ibmplexsanshebrew
            ),
            modifier = modifier,
            singleLine = true
        )
    }
}