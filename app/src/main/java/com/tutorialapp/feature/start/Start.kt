package com.tutorialapp.feature.start

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial

@Composable
fun Start(onLoadButtonClicked:() -> Unit, tutorials: List<Tutorial>){

    Column(){
        Button(onClick = onLoadButtonClicked) {

        }


        LazyColumn {
            itemsIndexed(tutorials) {index, item ->
                Row{
                    Text(item.title)
                    System.out.println(item.title)
                    Button(
                        onClick = {},
                    ){

                    }
                }

            }
        }
    }


}