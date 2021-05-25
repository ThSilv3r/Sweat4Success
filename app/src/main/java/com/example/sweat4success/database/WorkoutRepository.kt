package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao) {
    val readAllData: LiveData<List<WorkoutDb>> = workoutDao.readAllData()

    public fun getAll(){
        workoutDao.loadAll()
    }

    public suspend fun addWorkout(workout:WorkoutDb){
        workoutDao.addWorkout(workout)
    }

    public fun findByName(title: String): WorkoutDb{
        return workoutDao.findByName(title)
    }

    public fun findByTag(tagId: Int): List<WorkoutDb>{
        return workoutDao.findByTag(tagId)
    }

    public fun findById(id: Int): WorkoutDb{
        return workoutDao.findById(id)
    }

    public fun delete(workout: WorkoutDb){
        workoutDao.delete(workout)
    }

    public fun updateWorkout(workout: WorkoutDb){
        workoutDao.updateWorkout(workout)
    }
}