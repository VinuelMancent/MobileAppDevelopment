package com.tutorialapp.feature.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun StartScreen(viewModel: StartScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val tutorials by viewModel.getTutorials().observeAsState(emptyList())

    Start(viewModel::downloadOneTutorial,viewModel::loadTutorialsFromServer, tutorials)


}