package io.github.gabriel.androidjwtauth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.gabriel.androidjwtauth.ui.theme.JWTAuthKtorAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JWTAuthKtorAndroidTheme {

            }
        }
    }
}