package com.tutorialapp.feature.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorialapp.domain.SaveLocalTutorialUseCase
import kotlinx.coroutines.launch

class CreateScreenVewModel : ViewModel() {
    //funktion onCreateClicked --> wenn der user den button drückt
    //upload to online db
    /*
    fun SaveTutorialInOnlineDB(tutorial: Tutorial){
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
    */
    //upload to local db
    fun SaveTutorialInLocalDB(tutorial: com.tutorialapp.data.database.Tutorial) {
        viewModelScope.launch{
            SaveLocalTutorialUseCase()(tutorial)
        }
    }
}