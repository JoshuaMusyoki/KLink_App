package com.example.songabiz.presentation.screens.auth

import com.example.songabiz.data.models.AuthModelLogin
import com.example.songabiz.data.models.LoginModel
import com.example.songabiz.data.models.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationAPI {
    @Headers(
        "Accept: application/json"
    )
    @POST("login-user")
    fun login(@Body loginModel: LoginModel?): Call<AuthModelLogin?>?

    @Headers(
        "Accept: application/json"
    )
    @POST("create-user-account")
    fun registerUser(@Body registerModel: RegisterModel?): Call<AuthModelLogin?>?
}