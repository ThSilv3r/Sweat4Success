package com.example.sweat4success.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.WorkoutDao
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel

class WorkoutController: AppCompatActivity() {
    private var account: Account = Account()
    private lateinit var workoutDao: WorkoutDao
    private val workouts = mutableListOf<WorkoutDb>()


    fun getFavorites(user: UserDb, workoutViewModel: WorkoutViewModel): List<WorkoutDb> {
        var favoritesIdString = user.favoritesId
        var favorites = loadWorkouts(favoritesIdString.toString(), workoutViewModel)

        return favorites
    }
    fun getRecievedWorkouts(user: UserDb, workoutViewModel: WorkoutViewModel): List<WorkoutDb> {
        var workoutsIdString = user.recievedWorkouts
        var workouts = loadWorkouts(workoutsIdString.toString(), workoutViewModel)
        return workouts
    }

    fun getWorkouts(user: UserDb, workoutViewModel: WorkoutViewModel): List<WorkoutDb> {

        var workoutsIdString = user.workoutId
        var workouts = loadWorkouts(workoutsIdString.toString(), workoutViewModel);

        return workouts
    }

    fun loadWorkouts(workoutString: String,  workoutViewModel: WorkoutViewModel):List<WorkoutDb>{
        val workoutsIds = mutableListOf<Int>()
        var workoutsIdStrings = workoutString?.split(",")
        var i = workoutsIdStrings?.count()
        workoutsIdStrings = workoutsIdStrings?.drop(1)
        workoutsIdStrings?.forEach { workoutId ->
            var id = workoutId.replace("\\s".toRegex(), "")
            workoutsIds += id.toInt() }
        workoutsIds.forEach { workoutId ->
            var workout: WorkoutDb = workoutViewModel.findById(workoutId)
            workouts += workout
        }

        return workouts
    }
}