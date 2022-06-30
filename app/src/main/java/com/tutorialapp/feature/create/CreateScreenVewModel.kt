package com.tutorialapp.feature.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorialapp.domain.SaveLocalTutorialUseCase
import kotlinx.coroutines.launch

class CreateScreenVewModel : ViewModel() {

    fun saveTutorialInLocalDB(tutorial: com.tutorialapp.data.database.Tutorial) {
        viewModelScope.launch{
            SaveLocalTutorialUseCase()(tutorial)
        }
    }
}