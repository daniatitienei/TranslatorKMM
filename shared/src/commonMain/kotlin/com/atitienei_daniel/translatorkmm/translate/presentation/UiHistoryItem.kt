package com.atitienei_daniel.translatorkmm.translate.presentation

import com.atitienei_daniel.translatorkmm.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage
)