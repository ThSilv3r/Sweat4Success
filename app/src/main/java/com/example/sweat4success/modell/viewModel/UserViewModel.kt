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

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    public fun getAll(): List<UserDb> {
        return repository.getAll()

    }


    public fun addUser(user: UserDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }

    }

    public fun findByName(username: String, password: String): UserDb {
        return repository.findByName(username, password)
    }

    public fun getById(id: Int): UserDb {
        return repository.findById(id)
    }

    public fun deleteUser(user: UserDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    public fun updateUser(user: UserDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

}