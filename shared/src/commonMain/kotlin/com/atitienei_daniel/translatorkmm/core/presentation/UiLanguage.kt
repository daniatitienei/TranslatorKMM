package com.atitienei_daniel.translatorkmm.core.presentation

import com.atitienei_daniel.translatorkmm.core.domain.language.Language

expect class UiLanguage {
    val language: Language

    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}
