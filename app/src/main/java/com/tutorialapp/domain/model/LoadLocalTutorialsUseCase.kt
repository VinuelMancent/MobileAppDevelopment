package com.tutorialapp.domain.model

import com.tutorialapp.data.TutorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadLocalTutorialsUseCase(
    private val tutorialRepository: TutorialRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default){
        tutorialRepository.getAllTutorials()
    }
}