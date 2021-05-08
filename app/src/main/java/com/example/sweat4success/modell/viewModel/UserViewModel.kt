package com.example.sweat4success.modell.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val readAllData: LiveData<List<UserDb>>

    init{
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }


    fun addUser(user: UserDb){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }

    }

    fun findByName(username: String, password: String):UserDb {
            return repository.findByName(username, password)
    }

    fun getById(id: Int): UserDb {
        return repository.findById(id)
    }

    fun deleteUser(user: UserDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    fun updateUser(user: UserDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

}