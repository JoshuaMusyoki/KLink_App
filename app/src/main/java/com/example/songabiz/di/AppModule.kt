package com.example.songabiz.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("")
    .addConverterFactory(GsonConverterFactory.create())
    .build()