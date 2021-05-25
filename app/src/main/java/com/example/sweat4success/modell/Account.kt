package com.example.sweat4success.modell

import com.example.sweat4success.database.UserDb

class Account {
    companion object{
        var username: String = ""
        var password: String = ""
        lateinit var userList: List<UserDb>
        var friendName: String = ""
    }
    var email: String = "email"
    var age: Int = 0
    var bmi: Double = 0.0
    var height: Double= 0.0
    var weight: Double= 0.0
    var stomacheWidth: Double= 0.0
    var bicepsWidth: Double= 0.0
    var chestWidth: Double= 0.0
    var quadWidth: Double= 0.0
    var friendId: Int= 0
    var workoutId: Int= 0
    var favoritesId: Int= 0

    @JvmName("getUsername1")
    public fun getUsername(): String{
        return username
    }


    public fun setUsername(name: String) {
        username = name
    }

    public fun getPassword(): String {
        return password
    }

    public fun setPassword(password2: String){
        password = password2
    }

    public fun getUserList(): List<UserDb> {
        return userList
    }

    public fun setUserList(newUserList: List<UserDb>){
        userList = newUserList
    }
    public fun getFriendName(): String{
        return friendName
    }


    public fun setFriendName(name: String) {
        friendName = name
    }

}
