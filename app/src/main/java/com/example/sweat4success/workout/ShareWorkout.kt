package com.example.sweat4success.workout
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.account.UserController
import com.example.sweat4success.controller.DataController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.friend.FriendList
import com.example.sweat4success.modell.Account

class ShareWorkout:AppCompatActivity() {
    private var account: Account = Account();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shareworkout)
    }

    fun setUI() {
        val dc: DataController = DataController()
        var userList = account.getUserList();
        var username = "";
        var user = userList.find { it.username == username } as UserDb;
    }
}