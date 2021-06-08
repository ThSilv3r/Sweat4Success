package com.example.sweat4success.workout

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
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.TagDb
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.friends.FriendList
import com.example.sweat4success.friends.Userprofile
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.workout.descriptionWorkout
import kotlinx.android.synthetic.main.workout_without_pic.*
import kotlinx.android.synthetic.main.workout_without_pic.drawer_layout

class ViewWorkout: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mWorkoutViewModel: WorkoutViewModel
    private lateinit var mExerciseViewModel: ExerciseViewModel
    private lateinit var mTagViewModel: TagViewModel
    private lateinit var mUserViewModel: UserViewModel
    private var account = Account()
    private var workoutModel = Workouts()
    private lateinit var workout: WorkoutDb
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_without_pic)

        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel:: class.java)
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        workoutUser.setOnClickListener{
            val user = mUserViewModel.getById(workout.userId as Int)
            account.setFriendName(user.username.toString())
            startActivity(Intent(this, Userprofile::class.java))
        }

        this.createUIComponents()

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

    private fun createUIComponents(){

        var title = workoutModel.getWorkoutname()
        if(title == ""){
            title = "Test"
        }
        val exercises = mutableListOf<ExerciseDb>()
        val tags = mutableListOf<TagDb>()

        workout = mWorkoutViewModel.findByName(title)
        var workoutExerciseIds = workout.exerciseIds
        var workoutTagIds = workout.tagIds
        var workoutRepetitions = workout.repetitions
        workoutExerciseIds = workoutExerciseIds?.drop(1)
        workoutExerciseIds = workoutExerciseIds?.dropLast(1)
        workoutTagIds = workoutTagIds?.drop(1)
        workoutTagIds = workoutTagIds?.dropLast(1)
        workoutRepetitions = workoutRepetitions?.drop(1)
        workoutRepetitions = workoutRepetitions?.dropLast(1)

        textView24.text = workout.title
        descriptionWorkout.text = workout.description.toString()

        val exerciseIds = mutableListOf<Int>()
        val exerciseIdsString = workoutExerciseIds.toString()
        val exerciseIdsStrings = exerciseIdsString.split(",")
        exerciseIdsStrings.forEach { exerciseId ->
            val id = exerciseId.replace("\\s".toRegex(), "")
            exerciseIds += id.toInt()}
        exerciseIds.forEach {
                exerciseId ->
            val exercise: ExerciseDb = mExerciseViewModel.getById(exerciseId-1000)
            exercises += exercise
        }

        val repetitions = mutableListOf<Int>()
        val repetitionsString = workoutRepetitions.toString()
        val repetitionStrings = repetitionsString.split(",")
        repetitionStrings.forEach { repetition ->
            val rep = repetition.replace("\\s".toRegex(), "")
            repetitions += rep.toInt()}

        val tagIds = mutableListOf<Int>()
        val tagIdsString = workoutTagIds.toString()
        val tagIdsStrings = tagIdsString.split(",")
        tagIdsStrings.forEach { tagId ->
            val id = tagId.replace("\\s".toRegex(), "")
            tagIds += id.toInt()}
        tagIds.forEach {
                tagId ->
            val tag: TagDb = mTagViewModel.getById(tagId)
            tags += tag
        }


        if(!exercises.isEmpty()){
            exercises.forEach{exercise ->
                val workoutListLayout: LinearLayout = findViewById(R.id.exerciseLayout)
                val workoutExerciseRepetitionLayout: LinearLayout = findViewById(R.id.repetitionLayout)
                val exerciseLayout = LinearLayout(this)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                val exerciseTextView = TextView(this)
                val repetitionTextView = TextView(this)
                exerciseTextView.layoutParams = params
                repetitionTextView.layoutParams = params
                exerciseTextView.setTextColor(Color.WHITE)
                repetitionTextView.setTextColor(Color.WHITE)

                exerciseTextView.text = exercise.title
                repetitionTextView.text = repetitions[0].toString()
                exerciseLayout.addView(exerciseTextView)
                workoutExerciseRepetitionLayout.addView(repetitionTextView)
                workoutListLayout.addView(exerciseLayout)
                repetitions.drop(1)
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