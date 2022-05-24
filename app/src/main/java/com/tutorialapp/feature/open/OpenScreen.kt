package com.tutorialapp.feature.open

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tutorialapp.domain.Tutorial
import com.tutorialapp.domain.allTutorials

@Composable
fun OpenScreen(tutorial: List<Tutorial>){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        this.items(allTutorials.sortedBy { it.id }){ tutorial ->
            Open(tutorial)
        }
    }
}

@Preview
@Composable
fun OpenScreen_Preview() {
    OpenScreen(allTutorials)
}
