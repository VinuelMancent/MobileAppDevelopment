package com.tutorialapp.feature.open

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.TutorialToJson

var tutorials = loadTutorialsFromJson(TutorialToJson())

//open should show the name of the tutorial
@Composable
fun Open(){
    Column{
        ShowTutorials()
    }

}
@Composable
fun ShowTutorials(){
    //für jeden bereits vorhandenen schritt
    LazyColumn()
    {
        itemsIndexed(tutorials) {
                _, item -> Box(modifier = Modifier
                .padding(3.dp)
                .width(380.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .wrapContentHeight(align = Alignment.CenterVertically))
                {
                Text(text = (item.id.toString()),
                    Modifier
                        .padding(vertical = 3.dp)
                        .padding(horizontal = 5.dp), textDecoration = TextDecoration.Underline)
                Text(item.title,
                    Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 5.dp))
            }
        }
    }
}
fun loadTutorialsFromJson(jsonString: String): MutableList<Tutorial>{
    val gson = Gson()
    val ListTutorialType = object : TypeToken<List<Tutorial>>() {}.type
    var tutorials: MutableList<Tutorial> = gson.fromJson(jsonString, ListTutorialType)
    return tutorials
}