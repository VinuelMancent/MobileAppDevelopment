package com.tutorialapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TutorialDao {

    @Insert
    suspend fun insert(tutorial: TutorialDb)

    @Query("SELECT * FROM Tutorials")
    suspend fun getAll(): List<TutorialDb>

    @Query("SELECT * FROM Tutorials WHERE name = :name")
    suspend fun getByName(name: String): TutorialDb?

    //evtl noch lokale tutorials löschen?
}