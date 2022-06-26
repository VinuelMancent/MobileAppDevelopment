package com.tutorialapp.feature.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.tutorialapp.domain.Tutorial
import kotlin.reflect.KFunction2

@Composable
fun Start(onDownloadButtonClicked: KFunction2<Int, Tutorial, Unit>, onLoadButtonClicked:() -> Unit, tutorials: List<Tutorial>, localTutorials: List<com.tutorialapp.data.database.Tutorial>){

    Column(){
        Button(onClick = onLoadButtonClicked) {
            Text("Load Tutorials from the Web")
        }


        LazyColumn {
            itemsIndexed(tutorials) {index, item ->
                val buttonText : MutableState<String> = remember{ mutableStateOf<String>("Download")}
                val downloaded : Boolean = isLocallyAvailable(item.id, localTutorials)
                if(downloaded){
                    buttonText.value = "downloaded"
                }
                Row{
                    Text(item.title)
                    System.out.println(item.title)
                    Button(
                        onClick = {
                                  onDownloadButtonClicked(item.id, item)
                        },
                        enabled = !downloaded
                    ){
                        Text(buttonText.value)
                    }
                }

            }
        }
    }


}

private fun isLocallyAvailable(id: Int, localTutorials: List<com.tutorialapp.data.database.Tutorial>) : Boolean{
    for(tutorial in localTutorials){
        if(id == tutorial.id)
            return true;
    }
    return false;
}