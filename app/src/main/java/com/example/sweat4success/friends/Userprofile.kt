package com.example.sweat4success.friends

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sweat4success.R
import com.example.sweat4success.account.UserController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.friend.FriendController
import com.example.sweat4success.friend.FriendList
import com.example.sweat4success.modell.Account
import kotlinx.android.synthetic.main.viewprofile.*

class Userprofile:AppCompatActivity() {
    private var account:Account = Account();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewprofile)
    }
        fun setUI() {
        var userList = account.getUserList();
        var username = "";
        var user = userList.find{it.username==username} as UserDb;
        friendUserName.text = user.username;
        friendAge.text = user.age.toString();
       // friendWohnort.text = user.
   /*     var enabled;
        if (addFriend.isEnabled) {
            addFriend.isVisible = false
        }
     */
    var friendcont:FriendController = FriendController();
        addFriend.isSelected = friendcont.isFriend(user);

        addFriend.setOnClickListener()
    }
}

private fun ToggleButton.setOnClickListener() {
        if(isSelected) isSelected=false;
        else isSelected=true
}
