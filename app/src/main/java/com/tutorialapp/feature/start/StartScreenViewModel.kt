package com.tutorialapp.feature.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tutorialapp.data.network.WebService
import com.tutorialapp.domain.LoadLocalTutorialsUseCase
import com.tutorialapp.domain.SaveLocalTutorialUseCase
import com.tutorialapp.domain.Tutorial
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class StartScreenViewModel : ViewModel(){
    private val mutableLiveData = MutableLiveData<List<Tutorial>>(emptyList())

    fun getTutorials(): MutableLiveData<List<Tutorial>>{
        return mutableLiveData
    }

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

            mutableLiveData.value = webService.getAllTutorials().body()
        }
        return tutorials
    }

    fun getLocalTutorials()= liveData{
        val result = LoadLocalTutorialsUseCase()()
        emit(result)
    }


    fun downloadOneTutorial(id: Int, tutorial: Tutorial) {
        viewModelScope.launch{
            if(tutorial.id == id){
                try{
                    SaveLocalTutorialUseCase()(domainToDatabaseTutorial(tutorial))
                }catch(e: Exception){

                }

            }
        }
    }

    private fun domainToDatabaseTutorial(tutorial: Tutorial): com.tutorialapp.data.database.Tutorial {
        return com.tutorialapp.data.database.Tutorial(
            id = tutorial.id,
            title = tutorial.title,
            steps = domainStepsToDatabaseSteps(tutorial.steps),
            uploaded = true,
        )
    }
    private fun domainStepsToDatabaseSteps(steps: List<com.tutorialapp.domain.TutorialStep>) :List<com.tutorialapp.data.database.TutorialStep>{
        var result = mutableListOf<com.tutorialapp.data.database.TutorialStep>()
        for (element in steps) {
            result.add(
                com.tutorialapp.data.database.TutorialStep(
                    id = element.id,
                    content = element.content
                )
            )
        }
        return result
    }
}