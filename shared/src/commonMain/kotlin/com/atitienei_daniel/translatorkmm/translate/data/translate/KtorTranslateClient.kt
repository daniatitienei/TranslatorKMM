package com.atitienei_daniel.translatorkmm.translate.data.translate

import com.atitienei_daniel.translatorkmm.NetworkConstants
import com.atitienei_daniel.translatorkmm.core.domain.language.Language
import com.atitienei_daniel.translatorkmm.translate.domain.translate.TranslateClient
import com.atitienei_daniel.translatorkmm.translate.domain.translate.TranslateError
import com.atitienei_daniel.translatorkmm.translate.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorTranslateClient(
    private val httpClient: HttpClient
) : TranslateClient {

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        val result = try {
            httpClient.post {
                url(NetworkConstants.baseUrl + "/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        } catch (e: IOException) {
            throw TranslateException(TranslateError.ServiceUnavailable)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw TranslateException(TranslateError.ServerError)
            in 400..499 -> throw TranslateException(TranslateError.ClientError)
            else -> throw TranslateException(TranslateError.UnknownError)
        }

        return try {
            result.body<TranslatedDto>().translatedText
        } catch (e: Exception) {
            throw TranslateException(TranslateError.ServerError)
        }
    }

}