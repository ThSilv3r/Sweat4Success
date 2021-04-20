package com.example.sweat4success.modell.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sweat4success.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application):AndroidViewModel(application) {

    private val repository: WorkoutRepository
    val readAllData: LiveData<List<WorkoutDb>>;

    init{
        val workoutDao = WorkoutDataBase.getDatabase(application).workoutDao();
        repository = WorkoutRepository(workoutDao)
        readAllData = repository.readAllData
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll();
        }
    }


    fun addWorkout(workout: WorkoutDb){
        viewModelScope.launch(Dispatchers.IO){
            repository.addWorkout(workout);
        }

    }

    fun findByName(title: String): WorkoutDb {
        //viewModelScope.launch(Dispatchers.IO) {
        return repository.findByName(title);
        //}
    }

    fun findByTagId(tagId: Int): List<WorkoutDb> {
        //viewModelScope.launch(Dispatchers.IO) {
        return repository.findByTag(tagId);
        //}
    }

    fun findById(id: Int): WorkoutDb {
        //viewModelScope.launch(Dispatchers.IO) {
        return repository.findById(id);
        //}
    }

    fun deleteWorkout(workout: WorkoutDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(workout);
        }
    }

    fun updateWorkout(workout: WorkoutDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWorkout(workout);
        }
    }

}