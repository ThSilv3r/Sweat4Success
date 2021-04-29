package com.example.sweat4success.controller

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel

public class DataController: AppCompatActivity() {
    private var account: Account = Account();
    private lateinit var mWorkoutViewModel: WorkoutViewModel;
    private val favorites = mutableListOf<WorkoutDb>();
    private val workouts = mutableListOf<WorkoutDb>();


    public fun getFavorites(user: UserDb): List<WorkoutDb> {
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java);

        var favoritesIdString = user?.favoritesId;
        favoritesIdString = favoritesIdString?.drop(1);
        favoritesIdString = favoritesIdString?.dropLast(1);

        val favoritesIds = mutableListOf<Int>();
        val favoritesIdStrings = favoritesIdString?.split(",");
        favoritesIdStrings?.forEach { favoriteId -> favoritesIds += favoriteId.toInt() }
        favoritesIds.forEach { favoriteId ->
            var favorite: WorkoutDb = mWorkoutViewModel.findById(favoriteId) as WorkoutDb;
            favorites += favorite
        }

        return favorites;
    }

    public fun getWorkouts(user: UserDb): List<WorkoutDb> {
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java);

        var workoutsIdString = user?.workoutId;
        workoutsIdString = workoutsIdString?.drop(1);
        workoutsIdString = workoutsIdString?.dropLast(1);

        val workoutsIds = mutableListOf<Int>();
        val workoutsIdStrings = workoutsIdString?.split(",");
        workoutsIdStrings?.forEach { workoutId -> workoutsIds += workoutId.toInt() }
        workoutsIds.forEach { workoutId ->
            var workout: WorkoutDb = mWorkoutViewModel.findById(workoutId) as WorkoutDb;
            workouts += workout
        }

        return workouts;
    }
}