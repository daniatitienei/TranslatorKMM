package com.atitienei_daniel.translatorkmm.voice_to_text.presentation

import com.atitienei_daniel.translatorkmm.core.domain.util.toCommonStateFlow
import com.atitienei_daniel.translatorkmm.voice_to_text.domain.VoiceToTextParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VoiceToTextViewModel(
    private val parser: VoiceToTextParser,
    coroutineScope: CoroutineScope? = null
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(VoiceToTextState())
    val state = _state.combine(parser.state) { state, voiceResult ->
        state.copy(
            spokenText = voiceResult.result,
            recordError = voiceResult.error,
            displayState = when {
                voiceResult.error != null -> DisplayState.Error
                voiceResult.result.isNotBlank() && !voiceResult.isSpeaking -> {
                    DisplayState.DisplayingResults
                }

                voiceResult.isSpeaking -> DisplayState.Speaking
                else -> DisplayState.WaitingToTalk
            }
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = VoiceToTextState()
        )
        .toCommonStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                if (state.value.displayState == DisplayState.Speaking) {
                    _state.update {
                        it.copy(
                            powerRatios = it.powerRatios + parser.state.value.powerRatio
                        )
                    }
                }
                delay(50L)
            }
        }
    }

    fun onEvent(event: VoiceToTextEvent) {
        when (event) {
            is VoiceToTextEvent.PermissionResult -> {
                _state.update {
                    it.copy(canRecord = event.isGranted)
                }
            }

            VoiceToTextEvent.Reset -> {
                _state.update {
                    VoiceToTextState()
                }
            }
            is VoiceToTextEvent.ToggleRecording -> {
                toggleRecording(languageCode = event.languageCode)
            }
            else -> Unit
        }
    }

    private fun toggleRecording(languageCode: String) {
        parser.cancel()
        if (state.value.displayState == DisplayState.Speaking) {
            parser.stopListening()
        } else {
            parser.startListening(languageCode)
        }
    }
}