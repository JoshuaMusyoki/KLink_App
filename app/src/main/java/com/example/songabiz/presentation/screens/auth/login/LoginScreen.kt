package com.example.songabiz.presentation.screens.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.songabiz.R
import com.example.songabiz.presentation.theme.GreenPrimary
import com.example.songabiz.presentation.theme.ibmplexsanshebrew
import com.example.songabiz.presentation.theme.inter
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController : NavController,
    alpha: Float = 0.5f
){
    val couroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val content = LocalContext.current
    val basicAuthCredentials: BasicAuthCredentials? = BasicAuthCredentials(
        "email", "password"
    )

    val loginUser: () -> Unit = {
        couroutineScope.launch {
            try {
                val client = HttpClient  {
                    install(Auth){
                        basic {
                            credentials { basicAuthCredentials }
                        }
                    }
                    }
                navController.navigate("home_page_screen")
                } catch (e: Exception){
//                    Error Message
            }
        }
    }

    Scaffold (
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Black, GreenPrimary)
                    )
                )
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xFFD9D9D9), shape = CircleShape)
                    .border(
                        BorderStroke(
                            1.dp,
                            SolidColor(
                                Color(0xFFD9D9D9)
                            )
                        ),
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.usergreen),
                    contentDescription = "UserLogin_Placeholder",
                    modifier = Modifier
                        .width(72.75.dp)
                        .height(67.5.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "LOGIN",
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Email Address",
                fontFamily = ibmplexsanshebrew,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {
                        email = it
                    },
                    placeholder = {
                        Text(
                            text = "Email Address",
                            fontSize = 14.sp,
                            fontFamily = ibmplexsanshebrew
                        )
                    },
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
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Password",
                fontFamily = ibmplexsanshebrew,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        password = it
                    },
                    placeholder = {
                        Text(
                            text = "Enter Password",
                            fontSize = 14.sp,
                            fontFamily = ibmplexsanshebrew,
                        )
                    },
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
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            FilledTonalButton(
                modifier = Modifier
                    .height(50.dp)
                    .width(190.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    contentColor = Color.White
                )
                ,
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 16.sp,
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Row() {
                Text(
                     "Do you have an account?",
                    fontSize = 13.sp,
                    fontFamily = ibmplexsanshebrew
                )
               ClickableText(
                   text = AnnotatedString(
                       "Create Account.",
                       spanStyles = listOf(
                           AnnotatedString.Range(
                               SpanStyle(
                                   color = Color(0xFF0AFF7E),
                                   textDecoration = TextDecoration.Underline,
                                   fontSize = 13.sp,
                                   fontFamily = ibmplexsanshebrew
                               ), 0, 15
                           )
                       ),
                   ),
                       onClick = {
                           navController.navigate("signup_screen");
                       },
               )
            }
        }
    }
}
@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(navController = NavController(LocalContext.current))
}
