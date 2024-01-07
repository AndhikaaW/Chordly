package com.andhikaaw.chordly.database

import androidx.room.*

@Dao
interface LaguDao {
    @Insert
    suspend fun addLagu(lagu: Lagu)

    @Query("SELECT * FROM `Lagu` ORDER BY id DESC")
    suspend fun getAllLagu(): List<Lagu>

    @Update
    suspend fun updateLagu(lagu: Lagu)

    @Delete
    suspend fun deleteLagu(lagu: Lagu)
}