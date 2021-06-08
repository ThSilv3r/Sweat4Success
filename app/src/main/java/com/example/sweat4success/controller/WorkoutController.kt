package com.example.sweat4success.controller

import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.database.*
import com.example.sweat4success.modell.Account
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
        var workouts = loadWorkouts(workoutsIdString.toString(), workoutViewModel)

        return workouts
    }

    fun getWorkoutsByTag(workoutDao: WorkoutDao, tagId:Int): List<WorkoutDb>{
        val filterdWorkouts = mutableListOf<WorkoutDb>()
        var workouts = workoutDao.loadAll()
        workouts.forEach{
            workout->
            var workoutTagIds = workout.tagIds
            workoutTagIds = workoutTagIds?.drop(1)
            workoutTagIds = workoutTagIds?.dropLast(1)
            val tagIds = mutableListOf<Int>()
            val tagIdsString = workoutTagIds.toString()
            val tagIdsStrings = tagIdsString.split(",")
            tagIdsStrings.forEach { tagId ->
                val id = tagId.replace("\\s".toRegex(), "")
                tagIds += id.toInt()
            }
            if(tagIds.contains(tagId)){
                filterdWorkouts += workout
            }
        }
        return filterdWorkouts
    }

    private fun loadWorkouts(workoutString: String,  workoutViewModel: WorkoutViewModel):List<WorkoutDb>{
        val workoutsIds = mutableListOf<Int>()
        var workoutsIdStrings = workoutString.split(",")
        workoutsIdStrings = workoutsIdStrings.drop(1)
        workoutsIdStrings.forEach { workoutId ->
            var id = workoutId.replace("\\s".toRegex(), "")
            workoutsIds += id.toInt() }
        workoutsIds.forEach { workoutId ->
            var workout: WorkoutDb = workoutViewModel.findById(workoutId)
            workouts += workout
        }

        return workouts
    }
}