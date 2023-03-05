package com.atitienei_daniel.translatorkmm.voice_to_text.domain

import com.atitienei_daniel.translatorkmm.core.domain.util.CommonStateFlow

interface VoiceToTextParser {
    val state: CommonStateFlow<VoiceToTextParserState>
    fun startListening(languageCode: String)
    fun stopListening()
    fun cancel()
    fun reset()
}