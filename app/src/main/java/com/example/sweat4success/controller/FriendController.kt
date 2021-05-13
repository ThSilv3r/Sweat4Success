package com.example.sweat4success.controller

import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.database.UserDao
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel

class FriendController: AppCompatActivity(), DataController {
    private var account: Account = Account()
    private lateinit var user: UserDb
    private val friends = mutableListOf<UserDb>()
    private lateinit var userDao: UserDao

    fun addFriend(friend: UserDb, userViewModel: UserViewModel){
        user.friendId = user.friendId + ","+ friend.uid.toString()
        userViewModel.updateUser(user)
    }

    fun getFriend(id: Int, userViewModel: UserViewModel): UserDb{
        this.getItems(userViewModel)

        var user: UserDb = friends.find { it.uid == id }as UserDb

        return user
    }

    fun isFriend(friend: UserDb, userViewModel: UserViewModel): Boolean{
        var friendList = this.getItems(userViewModel)
        var isFriend: Boolean = friendList.contains(friend)
        return isFriend
    }

    fun getItems(userViewModel: UserViewModel): List<Any> {

        var userList = account.getUserList()
        var username = account.getUsername()
        var password = account.getPassword()
        user = userViewModel.findByName(username, password)

        var friendIdListString = user.friendId

        val friendIds = mutableListOf<Int>()
        var friendIdStrings = friendIdListString?.split(",")
        friendIdStrings = friendIdStrings?.drop(1)
        friendIdStrings?.forEach { friendId ->
            var id = friendId.replace("\\s".toRegex(), "")
            friendIds += id.toInt()}
        friendIds.forEach {
                friendId ->
            var friend: UserDb = userViewModel.getById(friendId)
            friends += friend
        }

        return friends
    }

    override fun getItems(): List<Any> {
        TODO("Not yet implemented")
    }
}