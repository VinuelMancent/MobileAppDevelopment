package com.tutorialapp.data.network

import com.tutorialapp.domain.Tutorial
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    //ToDo:ist das richtig??? müsste hier nicht ein string übergeben werden? unsicher...
    @POST("Add")
    suspend fun createTutorial(@Body json: Tutorial): Response<ResponseBody>

    @GET("getAll")
    suspend fun getAllTutorials(): MutableList<Tutorial>


    companion object {
        const val BASE_URL = "http://appservice-mongodb.herokuapp.com/mongo/";
    }
}

