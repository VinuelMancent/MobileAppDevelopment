package com.tutorialapp.data

import com.tutorialapp.App
import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.data.database.TutorialDao

val tutorialRepo = TutorialRepository(App.database.tutorialDao())

class TutorialRepository(
    private val dao: TutorialDao
) {
    suspend fun getAllTutorials(): List<Tutorial> = dao.getAll()
}