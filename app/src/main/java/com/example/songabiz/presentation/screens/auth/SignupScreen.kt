package com.example.songabiz.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.songabiz.R
import com.example.songabiz.presentation.theme.GreenPrimary
import com.example.songabiz.presentation.theme.ibmplexsanshebrew
import com.example.songabiz.presentation.theme.inter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavController, alpha:Float = 0.5F) {
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var emailAddress by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confPassword by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold {
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
        Surface (
            color = Color.Black,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha)
        ){}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
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
                                Color(0xFFD9D9D9)
                            )
                        ),
                        shape = CircleShape
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.userred),
                    contentDescription = "User Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(72.75.dp)
                        .height(67.5.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "SIGN UP",
                fontFamily = inter,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
            ) {
                Text(
                    text = "First Name",
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.width(110.dp))
                Text(
                    text = "Last Name",
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                OutlinedTextField(
                    value = fname,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        fname = it
                    },
                    placeholder = {
                        Text(
                            text = "First Name",
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
                        .width(170.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                OutlinedTextField(
                    value = sname,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        sname = it
                    },
                    placeholder = {
                        Text(
                            text = "Last Name",
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
                        .width(170.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(
                    text = "Email Address",
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                )
               OutlinedTextField(
                   value = emailAddress ,
                   keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                   onValueChange = {
                       emailAddress = it
                   },
                   placeholder = {
                       Text(
                           text = "Enter Email Address",
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
                   ),
                   textStyle = TextStyle(
                       fontSize = 14.sp,
                       fontFamily = ibmplexsanshebrew
                   ),
                   modifier = Modifier
                       .height(50.dp)
                   )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(
                    text = "Phone Number",
                    fontSize = 14.sp,
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )
                OutlinedTextField(
                    value = phone,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = {
                        phone = it
                    },
                   placeholder = {
                       Text(
                           text = "+254 XXX XXX XXX",
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
                        fontFamily = ibmplexsanshebrew,
                    ),
                    modifier = Modifier
                        .height(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(110.dp))
                Text(
                    text = "Confirm Password",
                    fontSize = 14.sp,
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                OutlinedTextField(
                    value = password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        password = it
                    },
                    placeholder = {
                        Text(
                            text = "EnterPassword",
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
                        fontFamily = ibmplexsanshebrew,
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(170.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                OutlinedTextField(
                    value = confPassword ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange ={
                        confPassword = it
                    },
                    placeholder = { Text(text = "Confirm Password", fontSize = 14.sp, fontFamily = ibmplexsanshebrew)},
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
                        .width(170.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            FilledTonalButton(
                modifier = Modifier
                    .height(50.dp)
                    .width(190.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                          navController.navigate("home_page_screen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "CREATE ACCOUNT",
                    fontSize = 16.sp,
                    fontFamily = ibmplexsanshebrew,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = "Already Have an Account?",
                    fontSize = 13.sp,
                    color = Color.White,
                    fontFamily = ibmplexsanshebrew
                )
                Spacer(modifier = Modifier.width(10.dp))
                ClickableText(
                    text = AnnotatedString(
                        "Log In.",
                        spanStyles = listOf(
                            AnnotatedString.Range(
                                SpanStyle(
                                    color = Color(0xFF0AFF7E),
                                    textDecoration = TextDecoration.Underline,
                                    fontSize = 13.sp,
                                    fontFamily = ibmplexsanshebrew
                                ), 0, 7
                            )
                        )
                    ),
                    onClick = {
                        navController.navigate("login_screen")
                    }
                )
            }
        }
    }
}
