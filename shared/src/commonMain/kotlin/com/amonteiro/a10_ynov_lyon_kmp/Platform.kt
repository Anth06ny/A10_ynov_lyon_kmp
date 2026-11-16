package com.amonteiro.a10_ynov_lyon_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform