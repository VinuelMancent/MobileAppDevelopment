package com.tutorialapp.feature.main.navigation

sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object CreateTutorial : BottomNavigationItem(){
        override val routeName = "create"
        override val title = 1
        override val icon = 2
    }

    object OpenTutorial : BottomNavigationItem(){
        override val routeName = "open"
        override val title = 11
        override val icon = 22
    }
}