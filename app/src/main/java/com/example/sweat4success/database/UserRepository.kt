package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao){


    val readAllData: LiveData<List<UserDb>> = userDao.readAllData()

    public fun getAll(): List<UserDb>{
        return userDao.loadAll()
    }

    public suspend fun addUser(user:UserDb){
        userDao.addUser(user)
    }

    public fun findByName(username: String, password: String): UserDb{
        return userDao.findByName(username, password)
    }

    public fun findById(id: Int): UserDb {
        return userDao.findById(id)
    }

    public fun delete(user: UserDb){
        userDao.delete(user)
    }

    public fun updateUser(user: UserDb){
        userDao.updateUser(user)
    }
}