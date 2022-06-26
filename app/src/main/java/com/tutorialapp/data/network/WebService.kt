package com.tutorialapp.data.network

import androidx.compose.runtime.MutableState
import com.tutorialapp.domain.Tutorial
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("Add")
    suspend fun createTutorial(@Body json: Tutorial): Response<ResponseBody>

    @GET("getAll")
    suspend fun getAllTutorials(): MutableList<Tutorial>

    @POST("getOne")
    suspend fun getOneTutorial(@Body id: Int): MutableState<com.tutorialapp.data.database.Tutorial>

    companion object {
        const val BASE_URL = "http://appservice-mongodb.herokuapp.com/mongo/";
    }
}

