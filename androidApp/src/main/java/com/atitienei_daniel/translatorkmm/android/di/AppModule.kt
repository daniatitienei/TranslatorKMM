package com.atitienei_daniel.translatorkmm.android.di

import android.app.Application
import com.atitienei_daniel.translatorkmm.database.TranslateDatabase
import com.atitienei_daniel.translatorkmm.translate.data.history.SqlDelightHistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.data.local.DatabaseDriverFactory
import com.atitienei_daniel.translatorkmm.translate.data.remote.HttpClientFactory
import com.atitienei_daniel.translatorkmm.translate.data.translate.KtorTranslateClient
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.domain.translate.Translate
import com.atitienei_daniel.translatorkmm.translate.domain.translate.TranslateClient
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClientFactory().create()

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient =
        KtorTranslateClient(httpClient)

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver =
        DatabaseDriverFactory(app).create()

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource =
        SqlDelightHistoryDataSource(TranslateDatabase(driver))

    @Provides
    @Singleton
    fun provideTranslateUseCase(client: TranslateClient, dataSource: HistoryDataSource): Translate =
        Translate(client, dataSource)
}