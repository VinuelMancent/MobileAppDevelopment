package com.tutorialapp.data.network

import com.tutorialapp.domain.Tutorial
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {

    @PUT("add")
    suspend fun createTutorial(@Body json: Tutorial): Response<ResponseBody>

    @GET("getAll")
    suspend fun getAllTutorials(): MutableList<Tutorial>


    companion object {
        const val BASE_URL = "http://10.0.2.2:80/mongo/";
    }
}

