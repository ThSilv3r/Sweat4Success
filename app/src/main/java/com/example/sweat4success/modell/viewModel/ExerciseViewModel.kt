package com.example.sweat4success.modell.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sweat4success.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel (application: Application): AndroidViewModel(application){
    private val repository: ExerciseRepository
    val readAllData: LiveData<List<ExerciseDb>>;

    init{
        val exerciseDao = ExerciseDataBase.getDatabase(application).exerciseDao();
        repository = ExerciseRepository(exerciseDao)
        readAllData = repository.readAllData
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll();
        }
    }


    fun addExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO){
            repository.addExercise(exercise);
        }

    }

    fun deleteExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(exercise);
        }
    }

    fun updateExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise);
        }
    }

}