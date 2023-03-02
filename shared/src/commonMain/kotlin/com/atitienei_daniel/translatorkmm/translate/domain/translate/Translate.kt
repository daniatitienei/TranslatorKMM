package com.atitienei_daniel.translatorkmm.translate.domain.translate

import com.atitienei_daniel.translatorkmm.core.domain.language.Language
import com.atitienei_daniel.translatorkmm.core.domain.util.Resource
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryItem

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String> =
        try {
            val translatedText = client.translate(
                fromLanguage = fromLanguage,
                fromText = fromText,
                toLanguage = toLanguage
            )
            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText
                )
            )
            Resource.Success(translatedText)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
}