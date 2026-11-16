package com.amonteiro.a10_ynov_lyon_kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amonteiro.a10_ynov_lyon_kmp.di.apiModule
import com.amonteiro.a10_ynov_lyon_kmp.di.viewModelModule
import com.amonteiro.a10_ynov_lyon_kmp.presentation.ui.AppNavigation
import com.amonteiro.a10_ynov_lyon_kmp.presentation.ui.theme.AppTheme
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(apiModule, viewModelModule)
    }) {
        AppTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AppNavigation(
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}