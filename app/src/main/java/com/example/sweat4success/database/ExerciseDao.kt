package com.example.sweat4success.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM ExerciseDb ORDER BY uid ASC")
    fun readAllData(): LiveData<List<ExerciseDb>>

    @Query("SELECT * FROM ExerciseDb")
    fun loadAll(): List<ExerciseDb>

    @Query("SELECT * FROM ExerciseDb WHERE uid LIKE :id")
    fun findById(id: Int):  ExerciseDb

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(user: ExerciseDb)

    @Update
    fun updateExercise(user: ExerciseDb)

    @Delete
    fun delete(user: ExerciseDb)
}