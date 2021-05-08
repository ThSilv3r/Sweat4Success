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

public class DataController: AppCompatActivity() {
    private var account: Account = Account();
    private lateinit var workoutDao: WorkoutDao;
    private val favorites = mutableListOf<WorkoutDb>();
    private val workouts = mutableListOf<WorkoutDb>();


    public fun getFavorites(user: UserDb, workoutViewModel: WorkoutViewModel): List<WorkoutDb> {
        var favoritesIdString = user?.favoritesId;

        val favoritesIds = mutableListOf<Int>();
        var favoritesIdStrings = favoritesIdString?.split(",");
        favoritesIdStrings = favoritesIdStrings?.drop(1);
        favoritesIdStrings?.forEach { favoriteId ->
            var id = favoriteId.replace("\\s".toRegex(), "")
            favoritesIds += id.toInt() }
        favoritesIds.forEach { favoriteId ->
            var favorite: WorkoutDb = workoutViewModel.findById(favoriteId) as WorkoutDb;
            favorites += favorite
        }

        return favorites;
    }

    public fun getWorkouts(user: UserDb, workoutViewModel: WorkoutViewModel): List<WorkoutDb> {

        var workoutsIdString = user?.workoutId;

        val workoutsIds = mutableListOf<Int>();
        var workoutsIdStrings = workoutsIdString?.split(",");
        var i = workoutsIdStrings?.count();
        workoutsIdStrings = workoutsIdStrings?.drop(1);
        workoutsIdStrings?.forEach { workoutId ->
            var id = workoutId.replace("\\s".toRegex(), "")
            workoutsIds += id.toInt() }
        workoutsIds.forEach { workoutId ->
            var workout: WorkoutDb = workoutViewModel.findById(workoutId) as WorkoutDb;
            workouts += workout
        }

        return workouts;
    }
}