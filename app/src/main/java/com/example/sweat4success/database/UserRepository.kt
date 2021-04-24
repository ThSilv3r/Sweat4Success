package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao){


    val readAllData: LiveData<List<UserDb>> = userDao.readAllData()

    fun getAll(){
        userDao.loadAll();
    }

    suspend fun addUser(user:UserDb){
        userDao.addUser(user);
    }

    fun findByName(username: String, password: String): UserDb{
        return userDao.findByName(username, password);
    }

    fun findById(id: Int): UserDb {
        return userDao.findById(id);
    }

    fun delete(user: UserDb){
        userDao.delete(user);
    }

    fun updateUser(user: UserDb){
        userDao.updateUser(user);
    }
}