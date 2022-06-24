package com.tutorialapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        TutorialDb::class,
    ],
)
abstract class AppDatabase:RoomDatabase(){
    abstract fun tutorialDao(): TutorialDao
}
