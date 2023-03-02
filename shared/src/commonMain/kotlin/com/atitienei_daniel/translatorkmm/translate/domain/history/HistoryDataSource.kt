package com.atitienei_daniel.translatorkmm.translate.domain.history

import com.atitienei_daniel.translatorkmm.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}