package com.example.viikko2mobiili

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.viikko2mobiili.ui.theme.Viikko2mobiiliTheme
import androidx.compose.ui.unit.dp
import com.example.viikko2mobiili.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko2mobiiliTheme {

                    HomeScreen(modifier = Modifier.fillMaxSize().padding(16.dp))

            }
        }
    }
}