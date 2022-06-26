package com.tutorialapp.domain

import com.tutorialapp.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadOnlineTutorialUseCase(
    private val webService: WebService,
){
    suspend operator fun invoke(id: Int) = withContext(Dispatchers.Default){
        webService.getOneTutorial(id)
    }
}