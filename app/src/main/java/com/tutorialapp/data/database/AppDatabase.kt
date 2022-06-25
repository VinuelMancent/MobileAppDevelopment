package com.tutorialapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 2,
    entities = [
        Tutorial::class,
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase(){
    abstract fun tutorialDao(): TutorialDao
}
