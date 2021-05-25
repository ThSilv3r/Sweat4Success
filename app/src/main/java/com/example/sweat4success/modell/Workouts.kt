package com.example.sweat4success.modell

import com.example.sweat4success.database.UserDb

class Workouts {
    companion object{
        var workoutName = ""
    }

    public fun getWorkoutname(): String{
        return Workouts.workoutName
    }


    public fun setWorkoutname(name: String) {
        Workouts.workoutName = name
    }
}