package com.tutorialapp.feature.open

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OpenScreen(viewModel: OpenScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val tutorials by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    //OpenScreenUI(tutorials)
    Open(viewModel::updateLocalTutorialToUploaded,viewModel::uploadToDB, tutorials)
}

@Preview
@Composable
fun OpenScreen_Preview() {
    OpenScreen()
}
