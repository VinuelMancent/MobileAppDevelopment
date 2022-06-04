package com.tutorialapp.domain

import com.tutorialapp.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadAllTutorialsUseCase(
  private val webService: WebService,
){
    suspend operator fun invoke() = withContext(Dispatchers.Default){
        webService.getAllTutorials()
    }
}