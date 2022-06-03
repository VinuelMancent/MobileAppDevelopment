package com.tutorialapp.domain

//ToDo: sich überlegen was alles in einem Tutorial vorhanden sein muss
data class Tutorial(
    val id: String,
    val title: String,
    val steps: List<TutorialStep>
)

//just an example for loading
val allTutorials = listOf(
    Tutorial(
        id = "1",
        title = "beispiel1",
        steps = listOf(
            TutorialStep(
                id = 1,
                content = "",
                //image = "",
            ),
            TutorialStep(
                id = 2,
                content = "",
                //image = "",
            )
        )
    ),
    Tutorial(
        id = "2",
        title = "Beispiel 2",
        steps = listOf(
            TutorialStep(
                id = 1,
                content = "",
                //image = "",
            ),
            TutorialStep(
                id = 2,
                content = "",
                //image = "",
            )
        )
    )
)