package com.example.sweat4success.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM WorkoutDb ORDER BY uid ASC")
    fun readAllData(): LiveData<List<WorkoutDb>>

    @Query("SELECT * FROM WorkoutDb")
    fun loadAll(): List<WorkoutDb>

    @Query("SELECT * FROM WorkoutDb WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): WorkoutDb

    @Query("SELECT * FROM WorkoutDb WHERE tagIds LIKE :tagId")
    fun findByTag(tagId: Int):  List<WorkoutDb>

    @Query("SELECT * FROM WorkoutDb WHERE uid LIKE :id")
    fun findById(id: Int): WorkoutDb

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: WorkoutDb)

    @Update
    fun updateWorkout(workout: WorkoutDb)

    @Delete
    fun delete(workout: WorkoutDb)
}