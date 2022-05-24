package com.tutorialapp.feature.main.model

import androidx.compose.runtime.mutableStateOf
import com.tutorialapp.domain.Tutorial

data class AppState(
    val loadedTutorials: List<Tutorial>,
)

var appState = mutableStateOf(
    AppState(
        loadedTutorials = emptyList(),
    )
)

fun loadTutorial(tutorial: Tutorial){
    appState.value = appState.value.copy(
        loadedTutorials = appState.value.loadedTutorials.plus(tutorial)
    )
}