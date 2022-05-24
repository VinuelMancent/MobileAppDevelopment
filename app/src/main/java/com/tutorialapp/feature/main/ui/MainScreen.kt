package com.tutorialapp.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tutorialapp.feature.main.model.AppState
import com.tutorialapp.feature.main.model.appState
import com.tutorialapp.feature.main.navigation.MainBottomNavigation
import com.tutorialapp.feature.main.navigation.MainNavigationGraph

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val state: AppState by remember { appState }

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            TopAppBar(
                title = {"Platzhalter"},
            )
        },
        bottomBar = {
            MainBottomNavigation(navController, state.loadedTutorials)
        }
    ){
            innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MainNavigationGraph(state, navController)
            }
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}