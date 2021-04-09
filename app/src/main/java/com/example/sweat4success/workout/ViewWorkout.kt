package com.example.sweat4success.workout

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.TagDb
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.createworkout.*
import kotlinx.android.synthetic.main.workout.*
import kotlinx.android.synthetic.main.workout.textView16
import kotlinx.android.synthetic.main.workout_without_pic.*

class ViewWorkout: AppCompatActivity() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel;
    private lateinit var mExerciseViewModel: ExerciseViewModel;
    private lateinit var mTagViewModel: TagViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_without_pic)

        this.createUIComponents()
    }

    private fun createUIComponents(){

        val title = "Test";
        val exercises = mutableListOf<ExerciseDb>()
        val tags = mutableListOf<TagDb>()

        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java);
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel:: class.java);
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java);

        val workout = mWorkoutViewModel.findByName(title);
        workout.exerciseIds?.dropLast(1);
        workout.exerciseIds?.drop(1);
        workout.tagIds?.drop(1);
        workout.tagIds?.dropLast(1);

        textView24.text = workout.title;
        textView16.text = workout.description.toString();
        //workoutDurationTextTime.text = workout.duration.toString();

        val exerciseIds = mutableListOf<Int>();
        val exerciseIdsString = exerciseIds.toString()
        val exerciseIdsStrings = workout.exerciseIds?.split(",");
        exerciseIdsStrings?.forEach { exerciseId -> exerciseIds += exerciseId.toInt()}
        exerciseIds.forEach {
                exerciseId ->
            var exercise: ExerciseDb = mExerciseViewModel.getById(exerciseId)
            exercises += exercise
        }

        val tagIds = mutableListOf<Int>();
        val tagIdsString = exerciseIds.toString()
        val tagIdsStrings = workout.exerciseIds?.split(",");
        tagIdsStrings?.forEach { tagId -> tagIds += tagId.toInt()}
        tagIds.forEach {
                tagId ->
            var tag: TagDb = mTagViewModel.getById(tagId)
            tags += tag
        }


        exercises.forEach{exercise ->
            var workoutListLayout: LinearLayout = findViewById(R.id.workoutExerciseList);
            var exerciseLayout: LinearLayout = LinearLayout(this);
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
            val exerciseTextView = TextView(this);
            val repetitionTextView = TextView(this);
            exerciseTextView.layoutParams = params;
            repetitionTextView.layoutParams = params;

            exerciseTextView.text = exercise.title
            repetitionTextView.text = exercise.repetitions.toString();
            exerciseLayout.addView(exerciseTextView);
            exerciseLayout.addView(repetitionTextView);
            workoutListLayout.addView(exerciseLayout);
        }
    }

}