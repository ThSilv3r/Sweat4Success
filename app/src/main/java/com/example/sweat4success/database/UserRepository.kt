package com.example.sweat4success.database

class UserRepository (private val userDao: UserDao){

    suspend fun addUser(user:UserDb){
        userDao.addUser(user);
    }

    fun findByName(username: String, password: String){
        userDao.findByName(username, password);
    }

    fun delete(user: UserDb){
        userDao.delete(user);
    }

    fun updateUser(user: UserDb){
        userDao.updateUser(user);
    }
}