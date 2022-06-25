package com.tutorialapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tutorial (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="steps") val steps: List<TutorialStep>,
    @ColumnInfo(name="uploaded") val uploaded: Boolean,
)
