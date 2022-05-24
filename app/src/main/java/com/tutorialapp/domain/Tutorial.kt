package com.tutorialapp.domain

data class Tutorial(
    val id: String,
    val steps: List<TutorialStep>
)

//just an example for loading
val allTutorials = listOf(
    Tutorial(
        id = "1",
        steps = listOf(
            TutorialStep(
                id = "1",
            ),
            TutorialStep(
                id = "2",
            )
        )
    ),
    Tutorial(
        id = "2",
        steps = listOf(
            TutorialStep(
                id = "1",
            ),
            TutorialStep(
                id = "2",
            )
        )
    )
)