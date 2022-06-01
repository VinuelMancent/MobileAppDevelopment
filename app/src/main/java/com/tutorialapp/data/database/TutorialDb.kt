package com.tutorialapp.data.database

import io.realm.annotations.PrimaryKey

data class TutorialDb(
    @PrimaryKey
    val id: String,
    @PrimaryKey
    val name: String,
)
