package com.example.sweat4success.workout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.controller.WorkoutController
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.friends.Userprofile
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.friendlist.*
import kotlinx.android.synthetic.main.workoutlist.*

class WorkoutList: AppCompatActivity() {

    private var workouts  = mutableListOf<WorkoutDb>()
    private var workoutController = WorkoutController()
    private val workoutTextViews = mutableListOf<TextView>()
    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var userViewModel: UserViewModel
    private var tagId: Int = 0;
    private var workoutModell = Workouts();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friendlist)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        tagId = workoutModell.getWorkoutTag()

        this.loadWorkouts()
        this.fillUI()

        workoutSearchButton.setOnClickListener {
            val name: String = searchFriendName.text.toString()
            searchWorkout(name)
        }
    }

    private fun loadWorkouts(){
        workouts = workoutController.getWorkoutsByTag(workoutViewModel, tagId)as MutableList<WorkoutDb>
    }

    private fun fillUI(){
        workoutTextViews.removeAll(workoutTextViews)
        if (!workouts.isEmpty()){
            workouts.forEach{
                    workout ->
                val workoutListLayout: LinearLayout = findViewById(R.id.workoutListLayout)
                val workoutTitle = TextView(this)
                workoutTitle.text = workout.title
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                workoutTitle.layoutParams = params
                workoutTitle.setTextColor(Color.WHITE)
                workoutTitle.isClickable = true


                workoutTitle.setOnClickListener{
                    workoutModell.setWorkoutname(workout.title.toString())
                    startActivity(Intent(this, Userprofile::class.java))
                }
                workoutListLayout.addView(workoutTitle)
                workoutTextViews += workoutTitle
            }
        }
    }

    private fun searchWorkout(name: String){
        if(name != ""){
            val workoutListLayout: LinearLayout = findViewById(R.id.workoutListLayout)
            workoutListLayout.removeAllViews()
            workoutTextViews.removeAll(workoutTextViews)
            val filterdFriends = workouts.filter { it.title == name }
            filterdFriends.forEach{
                    workout ->
                val workoutListLayout: LinearLayout = findViewById(R.id.workoutListLayout)
                val workoutName = TextView(this)
                workoutName.text = workout.title
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                workoutName.layoutParams = params
                workoutName.setTextColor(Color.WHITE)
                workoutName.isClickable = true


                workoutName.setOnClickListener{
                    workoutModell.setWorkoutname(workout.title.toString())
                    startActivity(Intent(this, Userprofile::class.java))
                }
                workoutListLayout.addView(workoutName)
                workoutTextViews += workoutName
            }
        }
    }


}