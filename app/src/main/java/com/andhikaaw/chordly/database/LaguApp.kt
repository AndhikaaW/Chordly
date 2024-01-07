package com.andhikaaw.chordly.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Lagu::class], version = 1)
abstract class LaguApp: RoomDatabase() {
    abstract fun getLaguDao(): LaguDao

    companion object{
        @Volatile
        private var instance : LaguApp? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LaguApp::class.java,
            name= "lirik_lagu"
        ).build()
    }
}