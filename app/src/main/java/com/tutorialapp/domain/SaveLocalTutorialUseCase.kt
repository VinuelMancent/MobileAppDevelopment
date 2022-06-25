package com.tutorialapp.domain

import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.data.tutorialRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveLocalTutorialUseCase {
    suspend operator fun invoke(tutorial: Tutorial) = withContext(Dispatchers.Default){
        tutorialRepo.insert(tutorial)
    }
}