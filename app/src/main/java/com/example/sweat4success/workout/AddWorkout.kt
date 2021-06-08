package com.example.sweat4success.workout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.friends.FriendList
import com.example.sweat4success.friends.Userprofile
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Exercise
import com.example.sweat4success.modell.Tag
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.createworkout.*
import kotlinx.android.synthetic.main.createworkout.drawer_layout
import kotlinx.android.synthetic.main.workout_without_pic.*
import java.io.IOException

class AddWorkout: AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mWorkoutViewModel: WorkoutViewModel
    private lateinit var mExerciseViewModel: ExerciseViewModel
    private lateinit var mTagViewModel: TagViewModel
    private lateinit var mUserViewModel: UserViewModel
    private var account: Account = Account()
    private var Tag: Tag = Tag()
    private var Exercise: Exercise = Exercise()
    var exerciseSwitchList = mutableListOf<Switch>()
    var repititionList = mutableListOf<EditText>()
    var tagSwitchList = mutableListOf<Switch>()
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.createworkout)

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

        this.createUIComponents()

        addWorkoutButton.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun createUIComponents(){
        var i = 1
        var id = 1001
        var id2 = 2001

        var tagList = Tag.getTagList()
        var exerciseList = Exercise.getExerciseList()

        if (tagList.count() != 0){
            tagList.forEach{
                    tag ->
                var tagLayout: LinearLayout = findViewById(R.id.addTagLayout)
                var tagSwitchButton = Switch(this)
                tagSwitchButton.text = tag.name
                tagLayout.addView(tagSwitchButton)
                tagSwitchButton.id = i
                tagSwitchButton.setTextColor(Color.WHITE)
                tagSwitchList.add(tagSwitchButton)
                i++
            }
        }

        if (exerciseList.count() != 0){
            exerciseList.forEach{
                    exercise ->
                var exerciseListLayout: LinearLayout = findViewById(R.id.addExerciseLayout)
                var exerciseLayout: LinearLayout = LinearLayout(this)


                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                var exerciseSwitchButton = Switch(this)
                exerciseSwitchButton.layoutParams = params
                exerciseSwitchButton.text = exercise.title
                exerciseSwitchButton.id = id
                exerciseSwitchButton.setTextColor(Color.WHITE)
                exerciseLayout.addView(exerciseSwitchButton)
                exerciseSwitchList.add(exerciseSwitchButton)


                var repetitionText = TextView(this)
                repetitionText.text = "Wiederholungen:"
                repetitionText.layoutParams = params
                repetitionText.setTextColor(Color.WHITE)
                exerciseLayout.addView(repetitionText)

                var repetition = EditText(this)
                repetition.id = id2
                exerciseLayout.addView(repetition)
                repititionList.add(repetition)

                exerciseListLayout.addView(exerciseLayout)
                id++
                id2++
            }
        }
    }


    private fun insertDataToDatabase() {
        var username = account.getUsername()
        var password = account.getPassword()
        var user: UserDb = mUserViewModel.findByName(username, password)as UserDb


        val exercises = exerciseSwitchList.filter { exerercise -> exerercise.isChecked }
        var repetitions = mutableListOf<Int>()
        val tags = tagSwitchList.filter { tag -> tag.isEnabled }
        val tagId = mutableListOf<Int>()
        val exerciseId = mutableListOf<Int>()

        exercises.forEach{
            exercise ->
            exerciseId += exercise.id
        }

        tags.forEach{
            tag ->
            tagId += tag.id
        }

        exercises.forEach{
            exercise ->
            var repetitionsEditText = repititionList.find { repetition -> repetition.id ==  exercise.id+1000}
            repetitions.add(Integer.parseInt(repetitionsEditText?.text.toString()))
        }

        var title: String = editWorkoutitle.text.toString()
        var description: String = editWorkoutDescription.text.toString()
        var duration: Editable? = editWorkoutDuration.text
        var tagIds: String = tagId.toString()
        var exerciseIds: String = exerciseId.toString()

        if(inputCheck(title, description, duration, tagIds, exerciseIds)){
            var workout: WorkoutDb = WorkoutDb(0, title, description, 0, tagIds, exerciseIds, user.uid, repetitions.toString())
            try {
                mWorkoutViewModel.addWorkout(workout)
                workout = mWorkoutViewModel.findByName(workout.title.toString())
                user.uid = user.uid
                user.workoutId  = user.workoutId + "," + workout.uid
                mUserViewModel.updateUser(user)
            }catch (e: IOException){
                throw e
            }
            Toast.makeText(this, "Succesfully created workout!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String, duration: Editable?, tagIds:String , exerciseIds:String): Boolean {
        return !(title == "" && description == "" && tagIds == "" && exerciseIds == "")
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