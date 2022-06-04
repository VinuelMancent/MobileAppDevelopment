package com.tutorialapp.feature.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val mutableLiveData = MutableLiveData<List<Tutorial>>(emptyList())

    //getter für mutableLiveData
    fun getTutorials(): MutableLiveData<List<Tutorial>>{
        return mutableLiveData
    }

    //updater für mutableLiveData
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

            mutableLiveData.value = webService.getAllTutorials()
            //tutorials = webService.getAllTutorials()
            //System.out.println(tutorials.toString())
            //allTutorialsAsObjects = Gson().fromJson(allTutorials, Array<Tutorial>::class.java)
        }
        System.out.println(tutorials)
        return tutorials
    }

    //zweite funktion mit livedata
    //return livedata objekt
    //kann in der ui verwendet werden
    //livedata muss upgedatet werden
    //viewmodel: mutablelivedata
}