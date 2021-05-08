package com.example.sweat4success.modell

import com.example.sweat4success.database.UserDb

public class Workouts(){
    companion object{
        var workoutName = "";
    }

    fun getWorkoutname(): String{
        return Workouts.workoutName
    }


    fun setWorkoutname(name: String) {
        Workouts.workoutName = name
    }
}