package com.tutorialapp.feature.open

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.TutorialStep
import kotlin.reflect.KFunction1

@Composable
fun Open(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit, onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorials: List<com.tutorialapp.data.database.Tutorial>){
    val tutorialOpen : MutableState<MutableMap<Int, MutableState<Boolean>>> = remember {mutableStateOf(initOpenMap(tutorials))}
    Column{
        ShowTutorials(updateTutorial,onUploadButtonClicked,tutorials, tutorialOpen)
    }
}

@Composable
fun ShowTutorials(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit,onUploadButtonClicked: KFunction1<Tutorial, Unit>,tutorials: List<com.tutorialapp.data.database.Tutorial>, tutorialOpen : MutableState<MutableMap<Int, MutableState<Boolean>>>){
    if(tutorials.isEmpty()){
        Text("You currently don't have any local Tutorials.")
        Text("You can either search and download one from the web or create your own!")
    }else {
        LazyColumn()
        {
            itemsIndexed(tutorials) { _, item ->
                ExpandableCard(
                    title = item.title,
                    steps = item.steps,
                    tutorial = item,
                    updateTutorial,
                    onUploadButtonClicked
                )

                if (tutorialOpen.value[item.id]?.value == true) {
                    ShowTutorialSteps(steps = item.steps)
                }
            }
        }
    }
}

@Composable
fun ShowTutorialSteps(steps : List<com.tutorialapp.data.database.TutorialStep>){
    for(step in steps){
        Text(step.content)
    }
    Log.e("", "am showing steps")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    title: String,
    steps: List<com.tutorialapp.data.database.TutorialStep>,
    tutorial: com.tutorialapp.data.database.Tutorial,
    updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit,
    onUploadButtonClicked: KFunction1<Tutorial, Unit>,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if(!tutorial.uploaded){
                    UploadButtonToDatabase(onUploadButtonClicked = onUploadButtonClicked, tutorial = tutorial, updateTutorial = updateTutorial)
                }
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                for(item in steps){
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                    ){
                        Box(
                            Modifier
                                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                .padding(vertical =  5.dp, horizontal = 8.dp)
                                .fillMaxWidth(0.9F)
                        ) {
                            Text(
                                text = item.content,
                                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UploadButtonToDatabase(updateTutorial : (com.tutorialapp.data.database.Tutorial) -> Unit, onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorial : com.tutorialapp.data.database.Tutorial){
    val buttonText = remember {mutableStateOf("Upload")}
    val buttonClickable = remember {mutableStateOf(true)}
    Button(onClick = {
        onUploadButtonClicked(databaseToDomainTutorial(tutorial))
        tutorial.uploaded = true
        buttonText.value = "Uploaded"
        updateTutorial(tutorial)
        buttonClickable.value = false
    }, Modifier
        .padding(horizontal = 5.dp)
    , enabled = buttonClickable.value
    ) {
        Text(buttonText.value)
    }
}

private fun databaseToDomainTutorial(tutorial: com.tutorialapp.data.database.Tutorial): Tutorial{
    val result = Tutorial(
        id = tutorial.id,
        title = tutorial.title,
        steps =  databaseStepsToDomainSteps(tutorial.steps)
    )
    return result
}

private fun databaseStepsToDomainSteps(steps: List<com.tutorialapp.data.database.TutorialStep>) : List<TutorialStep>{
    val result = mutableListOf<TutorialStep>()
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

private fun initOpenMap(tutorials: List<com.tutorialapp.data.database.Tutorial>) : MutableMap<Int, MutableState<Boolean>>{
    val tutorialOpen : MutableMap<Int, MutableState<Boolean>> =mutableMapOf<Int, MutableState<Boolean>>()
    for(tutorial in tutorials){
        tutorialOpen[tutorial.id]?.value = false
    }
    return tutorialOpen
}