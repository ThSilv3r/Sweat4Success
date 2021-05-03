package com.example.sweat4success.friends

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.controller.DataController
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import kotlinx.android.synthetic.main.viewprofile.*



class Userprofile:AppCompatActivity() {
    private var account: Account = Account();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewprofile)
    }

    fun setUI() {
        val dc: DataController = DataController()
        var userList = account.getUserList();
        var username = "";
        var user = userList.find { it.username == username } as UserDb;
        val favoritesid = user.favoritesId;
        friendUserName.text = user.username;
        friendAge.text = user.age.toString();
        var favorites = dc.getFavorites(user)
        var favoritesliste = "";

        favorites.forEach{
                favorite ->
            var friendListLayout: LinearLayout = findViewById(R.id.favoriteWorkoutLayout);
            var friendName: TextView = TextView(this);
            friendName.text = favorite.title;
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
        var workouts = dc.getWorkouts(user);
        var workoutliste = "";

        workouts.forEach{
                favorite ->
            var friendListLayout: LinearLayout = findViewById(R.id.workouts);
            var friendName: TextView = TextView(this);
            friendName.text = favorite.title;
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

        var friendcont: FriendController = FriendController();
        addFriend.isSelected = friendcont.isFriend(user);

        addFriend.setOnClickListener()
    }

}

private fun ToggleButton.setOnClickListener() {
        if(isSelected) isSelected=false;
        else isSelected=true
}


