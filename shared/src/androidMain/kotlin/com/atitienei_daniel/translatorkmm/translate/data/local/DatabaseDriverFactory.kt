package com.atitienei_daniel.translatorkmm.translate.data.local

import android.content.Context
import com.atitienei_daniel.translatorkmm.database.TranslateDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory constructor(
    private val context: Context
) {
    actual fun create(): SqlDriver =
        AndroidSqliteDriver(TranslateDatabase.Schema, context, "translate.db")
}