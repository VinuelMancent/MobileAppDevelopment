package com.tutorialapp.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

@Composable
fun MainNavigationGraph(state: Appstate, navController: NavHostController){
    NavHost(navController, startDestination = "create"){
        composable(BottomNavigationItem.CreateTutorial.routeName){

        }
    }
}