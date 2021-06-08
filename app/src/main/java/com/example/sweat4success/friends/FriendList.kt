package com.example.sweat4success.friends

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
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.workout.ShareWorkout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.delete.*
import kotlinx.android.synthetic.main.friendlist.*
import kotlinx.android.synthetic.main.friendlist.drawer_layout

class FriendList: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var friends  = mutableListOf<UserDb>()
    private val friendController: FriendController = FriendController()
    private val friendTextViews = mutableListOf<TextView>()
    private var account = Account()
    private lateinit var userViewModel: UserViewModel
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Profile", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Userprofile::class.java))
            }
            R.id.nav_friends ->{
                Toast.makeText(
                    baseContext,
                    "Friends",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, FriendList::class.java))
            }
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
            R.id.nav_share -> {
                startActivity(Intent(this, ShareWorkout::class.java))
                Toast.makeText(
                    baseContext,
                    "Share Workout",
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