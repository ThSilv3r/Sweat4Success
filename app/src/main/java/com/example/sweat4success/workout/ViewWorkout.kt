package com.example.sweat4success.workout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.TagDb
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.friends.Userprofile
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.workout.descriptionWorkout
import kotlinx.android.synthetic.main.workout_without_pic.*

class ViewWorkout: AppCompatActivity() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel
    private lateinit var mExerciseViewModel: ExerciseViewModel
    private lateinit var mTagViewModel: TagViewModel
    private lateinit var mUserViewModel: UserViewModel
    private var account = Account()
    private var workoutModel = Workouts()
    private lateinit var workout: WorkoutDb

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
    }

    private fun createUIComponents(){

        var title = workoutModel.getWorkoutname()
        if(title == ""){
            title = "Test"
        }
        val tags = mutableListOf<TagDb>()

        workout = mWorkoutViewModel.findByName(title)
        var workoutTagIds = workout.tagIds
        workoutTagIds = workoutTagIds?.drop(1)
        workoutTagIds = workoutTagIds?.dropLast(1)

        textView24.text = workout.title
        descriptionWorkout.text = workout.description.toString()

        createExerciseUi()

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

    }
    private fun createExerciseUi(){
        val exercises = mutableListOf<ExerciseDb>()
        var workoutExerciseIds = workout.exerciseIds
        var workoutRepetitions = workout.repetitions
        workoutExerciseIds = workoutExerciseIds?.drop(1)
        workoutExerciseIds = workoutExerciseIds?.dropLast(1)
        workoutRepetitions = workoutRepetitions?.drop(1)
        workoutRepetitions = workoutRepetitions?.dropLast(1)

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
            repetitions += rep.toInt()
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

}