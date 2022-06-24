package com.tutorialapp

import android.app.Application
import androidx.room.Room
import com.tutorialapp.data.database.AppDatabase

class App : Application() {

    override fun onCreate(){
        super.onCreate()
        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "app")
            //.apply {
            //    if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
            //}
            .build()
    }

    companion object{
        lateinit var database: AppDatabase
    }
}