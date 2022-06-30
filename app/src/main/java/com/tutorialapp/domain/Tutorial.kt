package com.tutorialapp.domain

import com.google.gson.Gson

data class Tutorial(
    val id: Int,
    val title: String,
    val steps: List<TutorialStep>
)

//just an example for loading
val allTutorials = listOf(
    Tutorial(
        id = 1,
        title = "beispiel1",
        steps = listOf(
            TutorialStep(
                id = 1,
                content = "content 1 beispiel 1",
                //image = "",
            ),
            TutorialStep(
                id = 2,
                content = "content 2 beispiel 1",
                //image = "",
            )
        )
    ),
    Tutorial(
        id = 2,
        title = "Beispiel 2",
        steps = listOf(
            TutorialStep(
                id = 1,
                content = "content 1 beispiel 2",
                //image = "",
            ),
            TutorialStep(
                id = 2,
                content = "content 2 beispiel 2",
                //image = "",
            )
        )
    )
)