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
    val readAllData: LiveData<List<ExerciseDb>>

    init{
        val exerciseDao = ExerciseDataBase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(exerciseDao)
        readAllData = repository.readAllData
    }

    public fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    public fun getById(id: Int): ExerciseDb{
            return repository.findById(id)
    }


    public fun addExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO){
            repository.addExercise(exercise)
        }

    }

    public fun deleteExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(exercise)
        }
    }

    public fun updateExercise(exercise: ExerciseDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise)
        }
    }

}