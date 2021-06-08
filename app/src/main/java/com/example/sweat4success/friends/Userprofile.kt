package com.example.sweat4success.friends

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.controller.WorkoutController
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import com.example.sweat4success.workout.ShareWorkout
import com.example.sweat4success.workout.ViewWorkout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.friendlist.*
import kotlinx.android.synthetic.main.viewprofile.*



class Userprofile:AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private var account: Account = Account()
    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var userViewModel: UserViewModel
    private var workoutModel = Workouts()
    private lateinit var user: UserDb
    private var isSelected: Boolean = false;
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewprofile)
        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setUI()
        addFriend.setOnClickListener{
            val friendcont = FriendController()
            var password = account.getPassword()
            var username = account.getUsername()
            var currentUser = userViewModel.findByName(username, password)
            if(isSelected){
                var friendList: List<UserDb> = friendcont.getItems(userViewModel) as List<UserDb>
                friendList += user
                friendList.forEach{
                        it  ->
                    currentUser.friendId = currentUser.friendId +  "," + it.uid
                }
                userViewModel.updateUser(currentUser)
            }else{
                var friendList: List<UserDb> = friendcont.getItems(userViewModel) as List<UserDb>
                friendList -= user
                if(friendList.count() != 0){
                    friendList.forEach{
                            it  ->
                        currentUser.friendId = currentUser.friendId +  "," + it.uid
                    }
                }else{
                    currentUser.friendId = ""
                }
                userViewModel.updateUser(currentUser)
            }
            isSelected = !isSelected
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

    private fun setUI() {
        val dc = WorkoutController()
        val userList = account.getUserList()
        val username = account.getFriendName()
        user = userList.find { it.username == username } as UserDb
        friendUserName.text = user.username
        friendAge.text = user.age.toString()
        val favorites = dc.getFavorites(user, workoutViewModel)

        favorites.forEach{
                favorite ->
            val favoriteListLayout: LinearLayout = findViewById(R.id.favoriteWorkoutLayout)
            val favoriteName = TextView(this)
            favoriteName.text = favorite.title
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            favoriteName.layoutParams = params
            favoriteName.setTextColor(Color.WHITE)
            favoriteName.isClickable = true


            favoriteName.setOnClickListener{
                workoutModel.setWorkoutname(favorite.title.toString())
                startActivity(Intent(this, ViewWorkout::class.java))
            }
            favoriteListLayout.addView(favoriteName)
        }
        val workouts = dc.getWorkouts(user, workoutViewModel)

        workouts.forEach{
                workout ->
            val workoutListLayout: LinearLayout = findViewById(R.id.workouts)
            val workoutName = TextView(this)
            workoutName.text = workout.title
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            workoutName.layoutParams = params
            workoutName.setTextColor(Color.WHITE)
            workoutName.isClickable = true


            workoutName.setOnClickListener{
                workoutModel.setWorkoutname(workout.title.toString())
                startActivity(Intent(this, ViewWorkout::class.java))
            }
            workoutListLayout.addView(workoutName)
        }

        val friendcont = FriendController()
        addFriend.isSelected = friendcont.isFriend(user, userViewModel)
        if(addFriend.isSelected){
            addFriend.toggle()
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
        if (drawer_layout1.isDrawerOpen(GravityCompat.START)) {
            drawer_layout1.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}


