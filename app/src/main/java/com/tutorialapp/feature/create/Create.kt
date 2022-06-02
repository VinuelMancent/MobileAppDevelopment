package com.tutorialapp.feature.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun Create(){

    Column {
        tutorialNameTextField()
        //Divider(color = Color.Blue, thickness = 1.dp)
        Column{
            addStep()
        }
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
@Composable
fun addStep(){
    var content by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = content,
        onValueChange = {content = it},
        label = { Text("Describe this step")},

    )
}