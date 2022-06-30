package com.tutorialapp.feature.main.navigation

import com.tutorialapp.R

sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Start: BottomNavigationItem(){
        override val routeName = "start"
        override val title = R.string.startscreen_navigation
        override val icon = R.drawable.ic_baseline_search_24 //platzhalter
    }
    object CreateTutorial : BottomNavigationItem(){
        override val routeName = "create"
        override val title = R.string.create_tutorial_navigation
        override val icon = R.drawable.ic_baseline_note_add_24
    }

    object OpenTutorial : BottomNavigationItem(){
        override val routeName = "open"
        override val title = R.string.open_tutorial_navigation
        override val icon = R.drawable.ic_baseline_home_24
    }
}