package com.example.sweat4success.friends

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.friendlist.*

class FriendList: AppCompatActivity() {

    private var friends  = mutableListOf<UserDb>()
    private val friendController: FriendController = FriendController()
    private val friendTextViews = mutableListOf<TextView>()
    private var account = Account()
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friendlist)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        this.loadFriends()
        this.fillUI()

        friendSearchButton.setOnClickListener {
            val name: String = searchFriendName.text.toString()
            searchFriend(name)
        }
    }


    private fun loadFriends(){
        friends = friendController.getItems(userViewModel)as MutableList<UserDb>
    }

    private fun fillUI(){
        friendTextViews.removeAll(friendTextViews)
        if (!friends.isEmpty()){
            friends.forEach{
                friend ->
                val friendListLayout: LinearLayout = findViewById(R.id.friendListLayout)
                val friendName = TextView(this)
                friendName.text = friend.username
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                friendName.layoutParams = params
                friendName.setTextColor(Color.WHITE)
                friendName.isClickable = true


                friendName.setOnClickListener{
                    account.setFriendName(friend.username.toString())
                    startActivity(Intent(this, Userprofile::class.java))
                }
                friendListLayout.addView(friendName)
                friendTextViews += friendName
            }
        }
    }

    private fun searchFriend(name: String){
        if(name != ""){
            val friendListLayout: LinearLayout = findViewById(R.id.friendListLayout)
            friendListLayout.removeAllViews()
            friendTextViews.removeAll(friendTextViews)
            val filterdFriends = friends.filter { it.username == name }
            filterdFriends.forEach{
                    friend ->
                val friendListLayout: LinearLayout = findViewById(R.id.friendListLayout)
                val friendName = TextView(this)
                friendName.text = friend.username
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                friendName.layoutParams = params
                friendName.setTextColor(Color.WHITE)
                friendName.isClickable = true


                friendName.setOnClickListener{
                    account.setFriendName(friend.username.toString())
                    startActivity(Intent(this, Userprofile::class.java))
                }
                friendListLayout.addView(friendName)
                friendTextViews += friendName
            }
        }
    }
}