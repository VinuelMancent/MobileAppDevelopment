package com.tutorialapp.feature.create


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tutorialapp.data.database.Tutorial
import com.tutorialapp.domain.TutorialStep
import java.util.*
import kotlin.math.abs

private var steps:MutableList<TutorialStep> = mutableStateListOf()
private var counter: Int = 1
private var title: String = ""
private var titleTextField: Unit = Unit


@Composable
fun Create(addTutorial: (Tutorial) -> Unit){
    Column {
        TutorialNameTextField()
        ShowExistingSteps(addTutorial)
    }
}

@Composable
fun TutorialNameTextField(){
    var value by remember { mutableStateOf(TextFieldValue("")) }
    titleTextField = OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
            title = newText.text
        },
        label = { Text(text = "Name of the tutorial")},
        singleLine = true,
        modifier = Modifier
            .padding(8.dp)
            .width(400.dp)
    )
}

@Composable
fun ShowExistingSteps(AddTutorial: (Tutorial) -> Unit){
    LazyColumn (
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
            )
    {
        itemsIndexed(steps) {
                index, item ->
            Box(modifier = Modifier
                .padding(3.dp)
                .width(380.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .wrapContentHeight(align = Alignment.CenterVertically)) {
                Text(text = (item.id.toString()),
                    Modifier
                        .padding(vertical = 3.dp)
                        .padding(horizontal = 5.dp), textDecoration = TextDecoration.Underline)
                Text(item.content,
                    Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 5.dp))
            }
        }
        item { AddStep() }
        item{ FinishCreatingTutorial(AddTutorial)}
    }
}

@Composable
fun AddStep(){
    val textFieldValue = ""
    var content by remember { mutableStateOf(TextFieldValue(textFieldValue)) }
    TextField(
        value = content,
        onValueChange = {content = it},
        label = { Text("Describe this step")},
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .width(400.dp)
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
    )
    Button(
        onClick = {
            if (content != TextFieldValue("")) {
                val step = TutorialStep(
                    id = counter,
                    content = content.text.trim(),
                )
                content = TextFieldValue("")
                counter++
                steps.add(step)
            }

        }, Modifier.padding(start = 8.dp)
    ){Text("Add Step")}
}
@Composable
fun FinishCreatingTutorial(AddTutorial: (Tutorial) -> Unit){
    Button(
        onClick = {
            var tutorial: Tutorial = Tutorial(
                id = generateID(),
                steps = mutableListToList(steps),
                title = title.substringAfter("(text=\""),
                uploaded = false,
            )
            AddTutorial(tutorial)
            steps.clear()
            counter = 0
        }, Modifier.padding(start = 8.dp)
    ){Text("Finish Tutorial")}
}

private fun generateID() : Int{
    val uuid = UUID.randomUUID()
    return abs(uuid.hashCode())
}

private fun mutableListToList(mutableList: MutableList<TutorialStep>): List<com.tutorialapp.data.database.TutorialStep>{
    var result: MutableList<com.tutorialapp.data.database.TutorialStep> = mutableListOf<com.tutorialapp.data.database.TutorialStep>()

    for (element in mutableList) {
       result.add(
           com.tutorialapp.data.database.TutorialStep(
               id = element.id,
               content = element.content
           )
       )
    }
    return result
}