package com.tutorialapp.data.database

import androidx.room.ColumnInfo

data class TutorialStep(
    @ColumnInfo(name="id")val id: Int,
    @ColumnInfo(name="content")val content: String,
)
