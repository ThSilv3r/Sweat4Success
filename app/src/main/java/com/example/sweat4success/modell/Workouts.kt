package com.example.sweat4success.modell

import com.example.sweat4success.database.UserDb

class Workouts {
    companion object{
        var workoutName = ""
        var workoutTagId = 0
    }

    fun getWorkoutname(): String{
        return Workouts.workoutName
    }


    fun setWorkoutname(name: String) {
        Workouts.workoutName = name
    }

    fun getWorkoutTag(): Int{
        return Workouts.workoutTagId
    }


    fun setWorkoutTag(name:Int) {
        Workouts.workoutTagId = name
    }
}