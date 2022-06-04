package com.tutorialapp.feature.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tutorialapp.domain.Tutorial

@Composable
fun Start(onLoadButtonClicked:() -> Unit){

    Button(onClick = onLoadButtonClicked) {
        
    }
}