package com.example.sweat4success.controller

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel

public class FriendController: AppCompatActivity() {
    private var account: Account = Account();
    private lateinit var user: UserDb;
    private val friends = mutableListOf<UserDb>();
    private lateinit var mUserViewModel: UserViewModel;


    public fun getFriends(): List<UserDb>{
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        var userList = account.getUserList()
        var username = account.getUsername();
        user = userList.find { it.username == username }as UserDb;

        var friendIdListString = user.friendId;
        friendIdListString = friendIdListString?.drop(1);
        friendIdListString = friendIdListString?.dropLast(1);

        val friendIds = mutableListOf<Int>();
        val friendIdStrings = friendIdListString?.split(",");
        friendIdStrings?.forEach { friendId -> friendIds += friendId.toInt()}
        friendIds.forEach {
                friendId ->
            var friend: UserDb = mUserViewModel.getById(friendId)
            friends += friend
        }

        return friends;
    }

    public fun addFriend(friend: UserDb){
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        user.friendId = user.friendId + ","+ friend.uid.toString();
        mUserViewModel.updateUser(user);
    }

    public fun getFriend(id: Int): UserDb{
        getFriends();

        var user: UserDb = friends.find { it.uid == id }as UserDb;

        return user;
    }

    public fun isFriend(friend: CharSequence): Boolean{
        var friendList = getFriends();
        var isFriend: Boolean = friendList.contains(friend);
        return isFriend;
    }
}