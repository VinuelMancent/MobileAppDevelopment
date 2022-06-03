package com.tutorialapp.domain

//ToDo: sich überlegen, was alles in einem TutorialSchritt vorhanden sein muss (Bilder, Texte, Id, ...)
data class TutorialStep(
    val id: Int,
    val content: String,
    //var image: String,
)
