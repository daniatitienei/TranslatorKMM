package com.atitienei_daniel.translatorkmm.translate.data.local

import com.atitienei_daniel.translatorkmm.database.TranslateDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver =
        NativeSqliteDriver(TranslateDatabase.Schema, "translate.db")
}