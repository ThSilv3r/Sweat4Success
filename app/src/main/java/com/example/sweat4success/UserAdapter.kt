package com.example.sweat4success

import com.example.sweat4success.database.UserDb

class UserAdapter {

    private var userList = emptyList<UserDb>()

    fun getItemCount(): Int{
        return userList.size
    }

    fun getUser(username: String, password: String){
        val user = userList.find { userDb ->  userDb.username == username && userDb.password == password}
    }
}