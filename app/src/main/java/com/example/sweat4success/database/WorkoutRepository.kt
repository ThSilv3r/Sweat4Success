package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao) {
    val readAllData: LiveData<List<WorkoutDb>> = workoutDao.readAllData()

    fun getAll(){
        workoutDao.loadAll()
    }

    suspend fun addWorkout(workout:WorkoutDb){
        workoutDao.addWorkout(workout)
    }

    fun findByName(title: String): WorkoutDb{
        return workoutDao.findByName(title)
    }

    fun findByTag(tagId: Int): List<WorkoutDb>{
        return workoutDao.findByTag(tagId)
    }

    fun findById(id: Int): WorkoutDb{
        return workoutDao.findById(id)
    }

    fun delete(workout: WorkoutDb){
        workoutDao.delete(workout)
    }

    fun updateWorkout(workout: WorkoutDb){
        workoutDao.updateWorkout(workout)
    }
}