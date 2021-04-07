package com.example.sweat4success.workout

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.createworkout.*
import kotlinx.android.synthetic.main.createworkout.view.*
import java.io.IOException

class AddWorkout: AppCompatActivity() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel;
    private lateinit var mExerciseViewModel: ExerciseViewModel;
    private lateinit var mTagViewModel: TagViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)
        setContentView(R.layout.createworkout)

        addWorkoutButton.setOnClickListener {
            insertDataToDatabase()
        }
    }


    private fun insertDataToDatabase() {
        var title: String = workoutTitleTextBox.text.toString();
        var description: String = workoutDescriptionTextbox.text.toString();
        var duration: Editable? = workoutDurationTextTime.text;
        var tagIds: String = ""/*tagsChipGroup.checkedChipId.toString();*/
        var exerciseIds: String = ""/*exerciseChipGroup.checkedChipId.toString()*/;

        if(inputCheck(title, description, duration, tagIds, exerciseIds)){
            var workout: WorkoutDb = WorkoutDb(0, title, description, 0, "", "", 0);
            try {
                mWorkoutViewModel.addWorkout(workout);
            }catch (e: IOException){
                throw e;
            }
            Toast.makeText(this, "Succesfully created account!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show();
        }
    }

    private fun inputCheck(title: String, description: String, duration: Editable?, tagIds:String , exerciseIds:String): Boolean {
        return !(title == "" && description == "" && tagIds == "" && exerciseIds == "")
    }
}