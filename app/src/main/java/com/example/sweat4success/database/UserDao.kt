package com.example.sweat4success.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM UserDb ORDER BY uid ASC")
    fun readAllData(): LiveData<List<UserDb>>

    @Query("SELECT * FROM UserDb")
    fun loadAll(): List<UserDb>

    @Query("SELECT * FROM UserDb WHERE username LIKE :username AND " + " password LIKE :password LIMIT 1")
    fun findByName(username: String, password: String): UserDb


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserDb)

    @Update
    fun updateUser(user: UserDb)

    @Delete
    fun delete(user: UserDb)
}