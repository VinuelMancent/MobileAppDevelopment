package com.tutorialapp.feature.main.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tutorialapp.feature.main.model.AppState
import com.tutorialapp.feature.main.navigation.MainBottomNavigation

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val state: AppState by remember { appState }
    Scaffold(
        topBar = {

        },
        bottomBar = {
            MainBottomNavigation(navController, state.loadedTutorials)
        }
    ){

    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}