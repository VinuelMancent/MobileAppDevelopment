package com.tutorialapp.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tutorialapp.domain.allTutorials
import com.tutorialapp.feature.create.CreateScreen
import com.tutorialapp.feature.main.model.AppState
import com.tutorialapp.feature.open.OpenScreen

@Composable
fun MainNavigationGraph(state: AppState, navController: NavHostController){
    NavHost(navController, startDestination = "create"){
        composable(BottomNavigationItem.CreateTutorial.routeName){
            CreateScreen()
        }
        composable(BottomNavigationItem.OpenTutorial.routeName){
            OpenScreen(allTutorials)
        }
    }
}