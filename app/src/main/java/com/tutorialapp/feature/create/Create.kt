package com.tutorialapp.feature.create

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun Create(){
    var value = "Tutorial Name"
    TextField(
        value = value,
        onValueChange = {value = it},
        label = { Text("Enter the name of the tutorial")}
    )
}