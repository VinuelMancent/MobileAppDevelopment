package com.tutorialapp.domain

import com.tutorialapp.data.tutorialRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadLocalTutorialsUseCase{
    suspend operator fun invoke() = withContext(Dispatchers.Default){
        tutorialRepo.getAllTutorials()
    }
}