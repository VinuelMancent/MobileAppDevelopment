package com.tutorialapp.feature.main.model

import androidx.compose.runtime.mutableStateOf
import com.tutorialapp.domain.Tutorial

data class AppState(
    val loadedTutorials: List<Tutorial>,
)

var appstate = mutableStateOf(
    AppState(
        loadedTutorials = emptyList(),
    )
)

fun loadTutorial(tutorial: Tutorial){
    appstate.value = appstate.value.copy(
        loadedTutorials = appstate.value.loadedTutorials.plus(tutorial)
    )
}