package com.example.sweat4success.friends

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.controller.WorkoutController
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import com.example.sweat4success.workout.ViewWorkout
import kotlinx.android.synthetic.main.viewprofile.*



class Userprofile:AppCompatActivity() {
    private var account: Account = Account()
    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var userViewModel: UserViewModel
    private var workoutModel = Workouts()
    private lateinit var user: UserDb
    private var isSelected: Boolean = false;


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
}


