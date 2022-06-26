package com.tutorialapp.feature.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.tutorialapp.domain.Tutorial
import kotlin.reflect.KFunction2

@Composable
fun Start(onDownloadButtonClicked: KFunction2<Int, Tutorial, Unit>, onLoadButtonClicked:() -> Unit, tutorials: List<Tutorial>){

    Column(){
        Button(onClick = onLoadButtonClicked) {
            Text("Load Tutorials from the Web")
        }


        LazyColumn {
            itemsIndexed(tutorials) {index, item ->
                Row{
                    Text(item.title)
                    System.out.println(item.title)
                    Button(
                        onClick = {
                                  onDownloadButtonClicked(item.id, item)
                        },
                    ){
                        Text("Download")
                    }
                }

            }
        }
    }


}