package com.tutorialapp.feature.open

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.TutorialStep
import kotlin.reflect.KFunction1

@Composable
fun Open(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit, onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorials: List<com.tutorialapp.data.database.Tutorial>){
    Column{
        ShowTutorials(updateTutorial,onUploadButtonClicked,tutorials)
    }

}
@Composable
fun ShowTutorials(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit,onUploadButtonClicked: KFunction1<Tutorial, Unit>,tutorials: List<com.tutorialapp.data.database.Tutorial>){
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
                if(!item.uploaded){
                    uploadButtonToDatabase(onUploadButtonClicked = onUploadButtonClicked, tutorial = item, updateTutorial = updateTutorial)
                }
            }

        }
    }
}

@Composable
fun uploadButtonToDatabase(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> kotlin.Unit,onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorial: com.tutorialapp.data.database.Tutorial){
    Button(onClick = {
        onUploadButtonClicked(databaseToDomainTutorial(tutorial))
        tutorial.uploaded = true
        updateTutorial(tutorial)
    }, Modifier
        .padding(top = 50.dp)
        .padding(horizontal = 5.dp)
        .padding(bottom = 5.dp)
    ) {
        Text("Upload")
    }
}

private fun databaseToDomainTutorial(tutorial: com.tutorialapp.data.database.Tutorial): Tutorial{
    var result: Tutorial = Tutorial(
        id = tutorial.id,
        title = tutorial.title,
        steps =  databaseStepsToDomainsteps(tutorial.steps)
    )
    return result
}

private fun databaseStepsToDomainsteps(steps: List<com.tutorialapp.data.database.TutorialStep>) : List<TutorialStep>{
    var result = mutableListOf<TutorialStep>()
    for (element in steps) {
        result.add(
            TutorialStep(
                id = element.id,
                content = element.content
            )
        )
    }
    return result
}