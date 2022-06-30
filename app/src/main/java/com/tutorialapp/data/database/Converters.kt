package com.tutorialapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    fun listOfStepsToDb(steps: List<TutorialStep>?): String?{
        if(steps == null){
            return (null)
        }
        return Gson().toJson(steps)
    }
    @TypeConverter
    fun listOfStepsFromDb(steps: String): List<TutorialStep>{
        if(steps == ""){
            return emptyList()
        }
        val itemType = object : TypeToken<List<TutorialStep>>() {}.type
        return Gson().fromJson<List<TutorialStep>>(steps, itemType)
    }
}