package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val readAllData: LiveData<List<ExerciseDb>> = exerciseDao.readAllData()

    fun getAll(){
        exerciseDao.loadAll();
    }

    suspend fun addExercise(exercise: ExerciseDb){
        exerciseDao.addExercise(exercise);
    }

    fun findById(id: Int): ExerciseDb {
        return exerciseDao.findById(id);
    }

    fun delete(exercise: ExerciseDb){
        exerciseDao.delete(exercise);
    }

    fun updateExercise(exercise: ExerciseDb){
        exerciseDao.updateExercise(exercise);
    }
}