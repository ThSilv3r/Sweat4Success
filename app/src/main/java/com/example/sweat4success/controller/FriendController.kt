package com.example.sweat4success.controller

import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.database.UserDao
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account

class FriendController: AppCompatActivity(), DataController {
    private var account: Account = Account()
    private lateinit var user: UserDb
    private val friends = mutableListOf<UserDb>()
    private lateinit var userDao: UserDao

    fun addFriend(friend: UserDb){
        user.friendId = user.friendId + ","+ friend.uid.toString()
        userDao.updateUser(user)
    }

    fun getFriend(id: Int): UserDb{
        this.getItems()

        var user: UserDb = friends.find { it.uid == id }as UserDb

        return user
    }

    fun isFriend(friend: UserDb): Boolean{
        var friendList = this.getItems()
        var isFriend: Boolean = friendList.contains(friend)
        return isFriend
    }

    override fun getItems(): List<Any> {

        var userList = account.getUserList()
        var username = account.getUsername()
        user = userList.find { it.username == username }as UserDb

        var friendIdListString = user.friendId
        friendIdListString = friendIdListString?.drop(1)
        friendIdListString = friendIdListString?.dropLast(1)

        val friendIds = mutableListOf<Int>()
        var friendIdStrings = friendIdListString?.split(",")
        friendIdStrings = friendIdStrings?.drop(1)
        friendIdStrings?.forEach { friendId ->
            var id = friendId.replace("\\s".toRegex(), "")
            friendIds += id.toInt()}
        friendIds.forEach {
                friendId ->
            var friend: UserDb = userDao.findById(friendId)
            friends += friend
        }

        return friends
    }
}