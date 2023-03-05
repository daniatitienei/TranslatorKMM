package com.atitienei_daniel.translatorkmm.voice_to_text.domain

data class VoiceToTextParserState(
    val result: String = "",
    var error: String? = null,
    var powerRatio: Float = 0f,
    val isSpeaking: Boolean = false
)
