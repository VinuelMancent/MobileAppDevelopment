package com.tutorialapp.data.database

import androidx.room.Entity
import io.realm.annotations.PrimaryKey

//every already loaded tutorial should get stored in the local DB
@Entity(tableName = "Tutorials")
data class TutorialDb(
    @PrimaryKey
    val id: String,
    @PrimaryKey
    val name: String,
    //Einzelschritte evtl direkt hier speichern, oder extra db anlegen?
)
