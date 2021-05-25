package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val readAllData: LiveData<List<ExerciseDb>> = exerciseDao.readAllData()

    public fun getAll(){
        exerciseDao.loadAll()
    }

    public suspend fun addExercise(exercise: ExerciseDb){
        exerciseDao.addExercise(exercise)
    }

    public fun findById(id: Int): ExerciseDb {
        return exerciseDao.findById(id)
    }

    public fun delete(exercise: ExerciseDb){
        exerciseDao.delete(exercise)
    }

    public fun updateExercise(exercise: ExerciseDb){
        exerciseDao.updateExercise(exercise)
    }
}