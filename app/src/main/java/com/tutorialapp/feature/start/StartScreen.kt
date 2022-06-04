package com.tutorialapp.feature.start

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import com.tutorialapp.feature.create.CreateScreenVewModel

@Composable
fun StartScreen(viewModel: StartScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Start(viewModel::loadTutorialsFromServer)
}