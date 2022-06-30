package com.tutorialapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

class TutorialDatabaseTest {
    @RunWith(AndroidJUnit4::class)
    class SimpleEntityReadWriteTest {
        private lateinit var tutorialDao: TutorialDao
        private lateinit var db: AppDatabase

        @Before
        fun createDb() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java).build()
            tutorialDao = db.tutorialDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        suspend fun writeTutorialAndReadInList() {
            val tut = Tutorial(
                id = 1234,
                title = "testTutorial",
                steps = listOf<TutorialStep>(TutorialStep(id = 1, content = "test")),
                uploaded = false,
            )
            tutorialDao.insert(tut)
            val tutorials = tutorialDao.getAll()
            assertThat(tutorials.size, equalTo(1))
            assertThat(tutorials[0].id, equalTo(1234))
            assertThat(tutorials[0].title, equalTo("testTutorial"))
        }
    }

}