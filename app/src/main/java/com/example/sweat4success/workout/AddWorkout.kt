package com.example.sweat4success.workout

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Exercise
import com.example.sweat4success.modell.Tag
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.createworkout.*
import java.io.IOException

class AddWorkout: AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.createworkout)

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
}