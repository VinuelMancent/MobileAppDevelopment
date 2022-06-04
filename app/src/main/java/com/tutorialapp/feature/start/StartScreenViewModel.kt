package com.tutorialapp.feature.start

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tutorialapp.data.network.WebService
import com.tutorialapp.domain.CreateTutorialUseCase
import com.tutorialapp.domain.Tutorial
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.ViewModel


class StartScreenViewModel : ViewModel(){
    fun loadTutorialsFromServer() : MutableList<Tutorial> {
        val loadTutorialsOnClick: () -> Unit = {
        }
        var tutorials: MutableList<Tutorial> = mutableListOf<Tutorial>()
        viewModelScope.launch {
            var webService: WebService = Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
                .create(WebService::class.java)


            tutorials = webService.getAllTutorials()
            System.out.println(tutorials.toString())
            //allTutorialsAsObjects = Gson().fromJson(allTutorials, Array<Tutorial>::class.java)
        }
        System.out.println(tutorials)
        return tutorials
    }
}