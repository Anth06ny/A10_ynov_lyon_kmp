package com.amonteiro.a10_ynov_lyon_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "A10_ynov_lyon_kmp",
    ) {
        App()
    }
}