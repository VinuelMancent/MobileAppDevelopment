package com.tutorialapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TutorialDao {

    @Insert
    suspend fun insert(tutorial: TutorialDb)

    @Query("SELECT * FROM Tutorial")
    suspend fun getAll(): List<TutorialDb>
}