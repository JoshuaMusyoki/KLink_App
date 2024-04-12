package com.example.songabiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.songabiz.presentation.navigation.Navigation
import com.example.songabiz.ui.theme.SongaBizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SongaBizTheme {
                Navigation()
                }
            }
        }
    }


