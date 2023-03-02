package com.atitienei_daniel.translatorkmm.translate.data.history

import com.atitienei_daniel.translatorkmm.core.domain.util.CommonFlow
import com.atitienei_daniel.translatorkmm.core.domain.util.toCommonFlow
import com.atitienei_daniel.translatorkmm.database.TranslateDatabase
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> =
        queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { history ->
                history.map { it.toHistoryItem() }
            }
            .toCommonFlow()

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toText = item.toText,
            toLanguageCode = item.toLanguageCode,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}

