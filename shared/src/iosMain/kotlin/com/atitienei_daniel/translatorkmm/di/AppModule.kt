package com.atitienei_daniel.translatorkmm.di

import com.atitienei_daniel.translatorkmm.database.TranslateDatabase
import com.atitienei_daniel.translatorkmm.translate.data.history.SqlDelightHistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.data.local.DatabaseDriverFactory
import com.atitienei_daniel.translatorkmm.translate.data.remote.HttpClientFactory
import com.atitienei_daniel.translatorkmm.translate.data.translate.KtorTranslateClient
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.domain.translate.Translate
import com.atitienei_daniel.translatorkmm.translate.domain.translate.TranslateClient

class AppModule {

    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    val translateUseCase: Translate by lazy {
        Translate(translateClient, historyDataSource)
    }
}