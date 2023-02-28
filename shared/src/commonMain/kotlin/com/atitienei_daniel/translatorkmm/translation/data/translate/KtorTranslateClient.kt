package com.atitienei_daniel.translatorkmm.translation.data.translate

import com.atitienei_daniel.translatorkmm.core.domain.language.Language
import com.atitienei_daniel.translatorkmm.translation.domain.translate.TranslateClient
import com.atitienei_daniel.translatorkmm.translation.domain.translate.TranslateError
import com.atitienei_daniel.translatorkmm.translation.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.utils.io.errors.IOException

class KtorTranslateClient(
    private val httpClient: HttpClient
): TranslateClient {

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        val result = try {
            httpClient.post {

            }
        } catch (e: IOException) {
            throw TranslateException(TranslateError.ServiceUnavailable)
        }
    }

}