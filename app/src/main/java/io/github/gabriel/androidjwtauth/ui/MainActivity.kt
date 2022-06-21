package io.github.gabriel.androidjwtauth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import io.github.gabriel.androidjwtauth.ui.theme.JWTAuthKtorAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JWTAuthKtorAndroidTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}