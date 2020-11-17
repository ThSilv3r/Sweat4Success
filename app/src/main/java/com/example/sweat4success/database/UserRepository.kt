package com.example.sweat4success.database

class UserRepository (private val userDao: UserDao){

    suspend fun addUser(user:UserDb){
        userDao.addUser(user);
    }
}