package com.example.sweat4success.friend

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.friendlist.view.*

class FriendList: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val friends  = mutableListOf<UserDb>();
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFriends()
        fillUI()

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.workouts_icon)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Profile", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, ContactsContract.Profile::class.java))
            }
            R.id.nav_friends -> {
                startActivity(Intent(this, FriendList::class.java))
                Toast.makeText(
                baseContext,
                "Friends",
                Toast.LENGTH_SHORT
            ).show()}
            R.id.nav_login -> {
                startActivity(Intent(this, LogIn::class.java))
                Toast.makeText(
                    baseContext,
                    "Login",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.nav_workouts -> {
                startActivity(Intent(this, Workout_Categories::class.java))
                Toast.makeText(
                    baseContext,
                    "Workout Categories",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}