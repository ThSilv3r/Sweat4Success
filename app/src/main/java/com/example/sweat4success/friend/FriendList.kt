package com.example.sweat4success.friend

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import kotlinx.android.synthetic.main.friendlist.*

class FriendList: AppCompatActivity() {

    private var friends  = mutableListOf<UserDb>();
    private val friendController: FriendController = FriendController();
    private val friendTextViews = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFriends();
        fillUI();

        friendSearchButton.setOnClickListener {
            var name: String = searchFriendName.text.toString();
            searchFriend(name);
        }

        loadFriends()
        fillUI()
    }


    fun loadFriends(){
        friends = friendController.getFriends()as MutableList<UserDb>;
    }

    fun fillUI(){
        friendTextViews.removeAll(friendTextViews);
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
                friendTextViews += friendName;
            }
        }
    }

    private fun searchFriend(name: String){
        if(name != ""){
            var friendListLayout: LinearLayout = findViewById(R.id.friendListLayout)
            friendListLayout.removeAllViews();
            friendTextViews.removeAll(friendTextViews);
            var filterdFriends = friends.filter { it.username == name }
            filterdFriends.forEach{
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
                friendTextViews += friendName;
            }
        }
    }
}