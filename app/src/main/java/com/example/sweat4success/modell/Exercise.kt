package com.example.sweat4success.modell

import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.UserDb

class Exercise {
    companion object{
        lateinit var exerciseList: List<ExerciseDb>
    }
    var title: String = ""
    var pictureURL: String = ""
    var description: String = ""


    fun getExerciseList(): List<ExerciseDb> {
        return Exercise.exerciseList
    }

    fun setExerciseList(newExerciseList: List<ExerciseDb>){
        exerciseList = newExerciseList
    }
}