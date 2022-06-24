package com.tutorialapp.feature.open

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.domain.LoadLocalTutorialsUseCase

class OpenScreenViewModel : ViewModel(){
    fun bindUi(context: Context): LiveData<List<Tutorial>> = liveData{
        val result = LoadLocalTutorialsUseCase()()
        emit(result)
    }
}