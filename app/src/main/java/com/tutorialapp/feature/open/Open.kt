package com.tutorialapp.feature.open

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.TutorialStep
import com.tutorialapp.domain.TutorialToJson

var tutorials = loadTutorialsFromJson(TutorialToJson())

//open should show the name of the tutorial
@Composable
fun Open(){
    Column{
        showTutorials()
    }

}
@Composable
fun showTutorials(){
    //für jeden bereits vorhandenen schritt
    LazyColumn()
    {
        itemsIndexed(tutorials) {
                _, item ->  Text(item.title)
        }
    }
}
fun loadTutorialsFromJson(jsonString: String): MutableList<Tutorial>{
    val gson = Gson()
    val ListTutorialType = object : TypeToken<List<Tutorial>>() {}.type
    var tutorials: MutableList<Tutorial> = gson.fromJson(jsonString, ListTutorialType)
    //gson.fromJson(jsonString, Tutorial::class.java)
    return tutorials
}