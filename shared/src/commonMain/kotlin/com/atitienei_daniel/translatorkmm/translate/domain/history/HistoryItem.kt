package com.atitienei_daniel.translatorkmm.translate.domain.history

data class HistoryItem(
    val id: Long?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String
)
