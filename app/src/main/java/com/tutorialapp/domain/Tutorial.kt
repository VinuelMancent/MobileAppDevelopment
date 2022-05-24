package com.tutorialapp.domain

data class Tutorial(
    val id: String,
    val steps: List<TutorialStep>
)
