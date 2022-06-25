package com.tutorialapp.feature.open

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.tutorialapp.data.database.Tutorial

@Composable
fun OpenScreen(viewModel: OpenScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val tutorials by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    //OpenScreenUI(tutorials)
    Open(tutorials)
}
@Composable
private fun OpenScreenUI(tutorials: List<Tutorial>){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        itemsIndexed(tutorials){ _, item ->
            Text(item.title)
        }
    }
}
@Preview
@Composable
fun OpenScreen_Preview() {
    OpenScreen()
}
