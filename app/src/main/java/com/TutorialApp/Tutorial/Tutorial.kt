package com.TutorialApp.Tutorial

data class Tutorial(
    val id: String,
    val name: String,
    val steps: List<TutorialStep>,
)
