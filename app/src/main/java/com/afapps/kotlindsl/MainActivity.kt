package com.afapps.kotlindsl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.afapps.kotlindsl.ui.theme.KotlinDSLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinDSLTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppInfo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppInfo(modifier: Modifier = Modifier) {
    Column(  modifier = modifier) {
        Text(
            text = "APPLICATION_ID: ${BuildConfig.APPLICATION_ID}",

        )
        Text(
            text = "BUILD_TYPE: ${BuildConfig.BUILD_TYPE}",
        )

        Text(
            text = "FLAVOR: ${BuildConfig.FLAVOR}",
        )

        Text(
            text = "VERSION_CODE: ${BuildConfig.VERSION_CODE}",
        )

        Text(
            text = "VERSION_NAME: ${BuildConfig.VERSION_NAME}",
        )

        Text(
            text = "BASE_URL: ${BuildConfig.BASE_URL}",
        )

        Text(
            text = "SERVER_URL: ${BuildConfig.SERVER_URL}",
        )

        Text(
            text = "ONESIGNAL_APP_ID: ${BuildConfig.ONESIGNAL_APP_ID}",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinDSLTheme {
        AppInfo()
    }
}