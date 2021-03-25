package com.example.sweat4success.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TagDao {
    @Query("SELECT * FROM TagDb ORDER BY uid ASC")
    fun readAllData(): LiveData<List<TagDb>>

    @Query("SELECT * FROM TagDb")
    fun loadAll(): List<TagDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTag(user: TagDb)

    @Delete
    fun delete(user: TagDb)
}