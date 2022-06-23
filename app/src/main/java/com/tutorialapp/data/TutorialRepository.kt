package com.tutorialapp.data

import com.tutorialapp.data.database.TutorialDao

//val tutorialRepo = TutorialRepository(App.database.)

class TutorialRepository(
    private val dao: TutorialDao
) {
    //suspend fun getAllTutorials(): List<Tutorial> = dao.getAll().mapNotNull{tutorialFromDb(it)}
}