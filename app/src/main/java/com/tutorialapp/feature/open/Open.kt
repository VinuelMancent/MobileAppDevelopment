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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.TutorialStep
import kotlin.reflect.KFunction1

@Composable
fun Open(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit, onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorials: List<com.tutorialapp.data.database.Tutorial>){
    var tutorialOpen : MutableState<MutableMap<Int, MutableState<Boolean>>> = remember {mutableStateOf(initOpenMap(tutorials))}
    Column{
        ShowTutorials(updateTutorial,onUploadButtonClicked,tutorials, tutorialOpen)
    }

}
@Composable
fun ShowTutorials(updateTutorial: (com.tutorialapp.data.database.Tutorial) -> Unit,onUploadButtonClicked: KFunction1<Tutorial, Unit>,tutorials: List<com.tutorialapp.data.database.Tutorial>, tutorialOpen : MutableState<MutableMap<Int, MutableState<Boolean>>>){
    LazyColumn()
    {
        itemsIndexed(tutorials) {
                _, item -> ExpandableCard(title = item.title, steps = item.steps, tutorial = item, updateTutorial, onUploadButtonClicked)


            /*Box(modifier = Modifier
            .padding(3.dp)
            .width(380.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .wrapContentHeight(align = Alignment.CenterVertically)
            .clickable(enabled = true) {
                //if clicked --> change value of map
                if (tutorialOpen.value[item.id] == null) {
                    tutorialOpen.value.put(item.id, mutableStateOf(false))
                }
                if (tutorialOpen.value[item.id]?.value == null) {
                    tutorialOpen.value[item.id]?.value = false
                }
                tutorialOpen.value[item.id]?.value = !tutorialOpen.value[item.id]?.value!!
                //change size depending on the value of the map
                if (tutorialOpen.value[item.id]?.value == true) {
                    //show Steps
                    //ShowTutorialSteps(steps = item.steps)
                }
            }
        )
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
            }*/
            if (tutorialOpen.value[item.id]?.value == true) {
                ShowTutorialSteps(steps = item.steps)
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
                    uploadButtonToDatabase(onUploadButtonClicked = onUploadButtonClicked, tutorial = tutorial, updateTutorial = updateTutorial)
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
                            .padding(8.dp),
                    ){
                        Text(
                            text = item.content,
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                /*
                Text(
                    text = description,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis
                )*/
            }
        }
    }
}
@Composable
fun uploadButtonToDatabase(updateTutorial : (com.tutorialapp.data.database.Tutorial) -> kotlin.Unit,onUploadButtonClicked: KFunction1<Tutorial, Unit>, tutorial : com.tutorialapp.data.database.Tutorial){
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

private fun initOpenMap(tutorials: List<com.tutorialapp.data.database.Tutorial>) : MutableMap<Int, MutableState<Boolean>>{
    var tutorialOpen : MutableMap<Int, MutableState<Boolean>> =mutableMapOf<Int, MutableState<Boolean>>()
    for(tutorial in tutorials){
        tutorialOpen[tutorial.id]?.value = false
    }
    return tutorialOpen
}