package com.atitienei_daniel.translatorkmm.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.translatorkmm.translate.domain.history.HistoryDataSource
import com.atitienei_daniel.translatorkmm.translate.domain.translate.Translate
import com.atitienei_daniel.translatorkmm.translate.presentation.TranslateEvent
import com.atitienei_daniel.translatorkmm.translate.presentation.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val historyDataSource: HistoryDataSource,
    private val translate: Translate,
) : ViewModel() {

    private val viewModel by lazy {
        TranslateViewModel(
            translate = translate,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: TranslateEvent) {
        viewModel.onEvent(event)
    }
}