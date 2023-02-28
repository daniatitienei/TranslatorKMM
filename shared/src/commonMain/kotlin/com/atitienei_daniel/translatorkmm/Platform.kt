package com.atitienei_daniel.translatorkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform