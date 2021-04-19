package com.example.sweat4success.friend

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import kotlinx.android.synthetic.main.friendlist.view.*

class FriendList: AppCompatActivity() {

    private val friends  = mutableListOf<UserDb>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFriends()
        fillUI()
    }


    fun loadFriends(){
        var account: Account = Account();
        var userList = account.getUserList();
        var username = account.getUsername();
        var user = userList.find{it.username == username}as UserDb;
        var friendIdString = user.friendId;

        friendIdString = friendIdString?.drop(1);
        friendIdString = friendIdString?.dropLast(1);
        val friendIds = mutableListOf<Int>();
        val exerciseIdsStrings = friendIdString?.split(",");
        exerciseIdsStrings?.forEach { friendId -> friendIds += friendId.toInt()}
        friendIds.forEach {
                friendId ->
            var user: UserDb = userList.find{it.uid  == friendId}as UserDb;
            friends += user;
        }
    }

    fun fillUI(){
        if (!friends.isEmpty()){
            friends.forEach{
                friend ->
                var friendListLayout: LinearLayout = findViewById(R.id.friendListLayout);
                var friendName: TextView = TextView(this);
                friendName.text = friend.username;
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
                friendName.layoutParams = params;
                friendName.setTextColor(Color.WHITE);
                friendName.isClickable = true;

                friendName.setOnClickListener{

                }
                friendListLayout.addView(friendName);

            }
        }
    }
}