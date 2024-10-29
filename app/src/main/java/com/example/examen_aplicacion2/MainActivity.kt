package com.example.examen_aplicacion2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.examen_aplicacion2.ui.theme.Examen_aplicacion2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen_aplicacion2Theme {
                ShoppingListScreen()
            }
        }
    }
}