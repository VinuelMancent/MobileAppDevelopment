package com.tutorialapp.feature.create

import androidx.compose.runtime.Composable

@Composable
fun CreateScreen(viewModel: CreateScreenVewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Create(viewModel::saveTutorialInLocalDB)
}