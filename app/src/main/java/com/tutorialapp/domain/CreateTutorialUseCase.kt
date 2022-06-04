package com.tutorialapp.domain

import com.tutorialapp.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateTutorialUseCase(
    private val webService: WebService,
){
    suspend operator fun invoke(tutorial: Tutorial) = withContext(Dispatchers.Default){
        webService.createTutorial(tutorial)
    }
}