package com.atitienei_daniel.translatorkmm.translate.domain.translate

import com.atitienei_daniel.translatorkmm.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}