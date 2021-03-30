package com.example.sweat4success.workout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sweat4success.R
import com.example.sweat4success.database.WorkoutDb
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.android.synthetic.main.createworkout.*
import kotlinx.android.synthetic.main.createworkout.view.*
import java.io.IOException

class AddWorkout: Fragment() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel;
    private lateinit var mExerciseViewModel: ExerciseViewModel;
    private lateinit var mTagViewModel: TagViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.createworkout, container, false)

        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)




        view.addWorkoutButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        var title: String = workoutTitleTextBox.text.toString();
        var description: String = workoutDescriptionTextbox.text.toString();
        var duration: Editable? = workoutDurationTextTime.text;
        var tagIds: String = tagsChipGroup.checkedChipIds.toString();
        var exerciseIds: String = exerciseChipGroup.checkedChipIds.toString();

        if(inputCheck(title, description, duration, tagIds, exerciseIds)){
            var workout: WorkoutDb = WorkoutDb(0, title, description, 0, "", "", 0);
            try {
                mWorkoutViewModel.addWorkout(workout);
            }catch (e: IOException){
                throw e;
            }
            Toast.makeText(requireContext(), "Succesfully created account!", Toast.LENGTH_LONG).show();
            findNavController().navigate(R.id.action_addWorkout_to_workoutListFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show();
        }
    }

    private fun inputCheck(title: String, description: String, duration: Editable?, tagIds:String , exerciseIds:String): Boolean {
        return !(title == "" && description == "" && tagIds == "" && exerciseIds == "")
    }
}