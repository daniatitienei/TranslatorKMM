package com.atitienei_daniel.translatorkmm.translation.domain.translate

enum class TranslateError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class TranslateException(error: TranslateError): Exception(
    message = "An error occurred when translating"
)