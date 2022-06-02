package com.tutorialapp.feature.create


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.tutorialapp.domain.TutorialStep


//test to simply try to print steps
val step1 = TutorialStep(
    id = 1,
    content = "c1",
    image = "",
)
val step2 = TutorialStep(
    id = 2,
    content = "c2",
    image = "",
)
val step3 = TutorialStep(
    id = 3,
    content = "c3",
    image = "",
)

//


private var steps:MutableList<TutorialStep> = mutableStateListOf(step1, step2, step3)
private var counter: Int = 0


@Composable
fun Create(){

    Column {
        tutorialNameTextField()
        //Divider(color = Color.Blue, thickness = 1.dp)
        showExistingSteps()
        //Divider(color = Color.Blue, thickness = 1.dp)
        addStep()
    }
}

@Composable
fun tutorialNameTextField(){
    var value by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Name of the tutorial")},
        maxLines = 1,
    )
}

/*
@Composable
fun showExistingSteps(){
    //für jeden bereits vorhandenen schritt
    steps.forEach{
        Text(it.id.toString())
        Text(it.content)
    }
}*/

@Composable
fun showExistingSteps(){
    //für jeden bereits vorhandenen schritt
    LazyColumn (
        Modifier.fillMaxWidth(),
            )
    {
        itemsIndexed(steps) {
            index, item ->  Text(item.content)
        }

    }
}

@Composable
fun addStep(){
    var textFieldValue: String = ""
    var content by remember { mutableStateOf(TextFieldValue(textFieldValue)) }
    TextField(
        value = content,
        onValueChange = {content = it},
        label = { Text("Describe this step")},
    )
    Button(
        onClick = {
            var step = TutorialStep(
                id = counter,
                content = content.text,
                image =  ""
            )
            textFieldValue = ""
            counter++
            steps.add(step)
            //hinzufügen klappt, es muss nur auch jedes mal neu geladen werden
            reload()
        }
    ){}
}

fun reload(){

}