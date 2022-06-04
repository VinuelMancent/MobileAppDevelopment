package com.tutorialapp.feature.create

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSerializer
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tutorialapp.data.network.WebService
import com.tutorialapp.domain.CreateTutorialUseCase
import com.tutorialapp.domain.Tutorial
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateScreenVewModel : ViewModel() {
    //funktion onCreateClicked --> wenn der user den button drückt

    fun test(tutorial: Tutorial){
        val saveTutorialOnClick: () -> Unit = {
        }

        viewModelScope.launch {
                var webService: WebService = Retrofit.Builder()
                    .baseUrl(WebService.BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create()
                    )
                    .build()
                    .create(WebService::class.java)


                System.out.println(Gson().toJson(tutorial))
                var useCase = CreateTutorialUseCase(webService)
                var result = useCase.invoke(tutorial)
                System.out.println(result.message())
                System.out.println(result.headers().toString())
                System.out.println(result.body().toString())
            }
    }
}