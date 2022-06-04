package com.tutorialapp.feature.start

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.tutorialapp.feature.create.CreateScreenVewModel

@Composable
fun StartScreen(viewModel: StartScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val tutorials by viewModel.getTutorials().observeAsState(emptyList())

    Start(viewModel::loadTutorialsFromServer, tutorials)


}