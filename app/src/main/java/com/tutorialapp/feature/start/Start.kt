package com.tutorialapp.feature.start

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial
import kotlin.reflect.KFunction2

@Composable
fun Start(onDownloadButtonClicked: KFunction2<Int, Tutorial, Unit>, onLoadButtonClicked:() -> Unit, tutorials: List<Tutorial>?, localTutorials: List<com.tutorialapp.data.database.Tutorial>?){

    Column {
        Button(onClick = onLoadButtonClicked) {
            Text("Load Tutorials from the Web")
        }

        if(tutorials != null) {


            LazyColumn {
                itemsIndexed(tutorials!!) { _, item ->
                    val buttonText: MutableState<String> =
                        remember { mutableStateOf<String>("Download") }
                    val downloaded: Boolean = isLocallyAvailable(item.id, localTutorials)
                    if (downloaded) {
                        buttonText.value = "downloaded"
                    }
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(380.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    {
                        Text(
                            item.title,
                            Modifier
                                .padding(vertical = 3.dp)
                                .padding(horizontal = 5.dp),
                            textDecoration = TextDecoration.Underline
                        )
                        Text(
                            "steps: " + (item.steps.lastIndex + 1).toString(),
                            Modifier
                                .padding(top = 25.dp)
                                .padding(horizontal = 5.dp)
                                .padding(bottom = 5.dp)
                        )
                        Button(
                            onClick = {
                                onDownloadButtonClicked(item.id, item)
                            },
                            Modifier
                                .align(alignment = Alignment.CenterEnd)
                                .padding(end = 5.dp),
                            enabled = !downloaded
                        ) {
                            Text(buttonText.value)
                        }
                    }
                }
            }
        }
    }
}

private fun isLocallyAvailable(id: Int, localTutorials: List<com.tutorialapp.data.database.Tutorial>?) : Boolean{
    if (localTutorials != null) {
        for(tutorial in localTutorials){
            if(id == tutorial.id)
                return true
        }
    }
    return false
}