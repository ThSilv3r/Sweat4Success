package com.example.sweat4success.workout
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.controller.DataController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.modell.Account
import kotlinx.android.synthetic.main.shareworkout.view.*

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
    private fun Button.setOnClickListener() {
        val fc: FriendController = FriendController()
        var friend = friendtosharewith.text;
        if (fc.isFriend(friend)){
            // quo vadis
        }
    }

}