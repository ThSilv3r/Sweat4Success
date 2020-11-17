package com.example.sweat4success.modell

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    init{
        val userDao = AppDatabase.getDatabase(application).userDao();
        repository = UserRepository(userDao)
    }

    fun addUser(user: UserDb){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user);
        }

    }
}