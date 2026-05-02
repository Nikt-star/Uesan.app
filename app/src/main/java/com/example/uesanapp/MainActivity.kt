package com.example.uesanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.uesanapp.presentation.presentation.navigation.AppNavGraph
import com.example.uesanapp.ui.theme.UesanappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UesanappTheme {
                AppNavGraph()
            }
        }
    }
}
