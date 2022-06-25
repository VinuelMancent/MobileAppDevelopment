package com.tutorialapp.feature.open

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.data.network.WebService
import com.tutorialapp.domain.LoadLocalTutorialsUseCase
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenScreenViewModel : ViewModel(){
    fun bindUi(context: Context): LiveData<List<Tutorial>> = liveData{
        val result = LoadLocalTutorialsUseCase()()
        emit(result)
    }

    fun uploadToDB(tutorial: com.tutorialapp.domain.Tutorial){
        viewModelScope.launch {
            var webService: WebService = Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
                .create(WebService::class.java)

            webService.createTutorial(tutorial)
        }
    }
}