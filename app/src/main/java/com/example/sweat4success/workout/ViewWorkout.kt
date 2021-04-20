package com.example.sweat4success.workout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.TagDb
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.workout.descriptionWorkout
import kotlinx.android.synthetic.main.workout_without_pic.*

class ViewWorkout: AppCompatActivity() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel;
    private lateinit var mExerciseViewModel: ExerciseViewModel;
    private lateinit var mTagViewModel: TagViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_without_pic)

        workoutUser.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

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
        var workoutExerciseIds = workout.exerciseIds;
        var workoutTagIds = workout.tagIds;
        var workoutRepetitions = workout.repetitions;
        workoutExerciseIds = workoutExerciseIds?.drop(1);
        workoutExerciseIds = workoutExerciseIds?.dropLast(1);
        workoutTagIds = workoutTagIds?.drop(1);
        workoutTagIds = workoutTagIds?.dropLast(1);
        workoutRepetitions = workoutRepetitions?.drop(1);
        workoutRepetitions = workoutRepetitions?.dropLast(1);

        textView24.text = workout.title;
        descriptionWorkout.text = workout.description.toString();
        //workoutDurationTextTime.text = workout.duration.toString();

        val exerciseIds = mutableListOf<Int>();
        val exerciseIdsString = workoutExerciseIds.toString()
        val exerciseIdsStrings = exerciseIdsString?.split(",");
        exerciseIdsStrings?.forEach { exerciseId -> exerciseIds += exerciseId.toInt()}
        exerciseIds.forEach {
                exerciseId ->
            var exercise: ExerciseDb = mExerciseViewModel.getById(exerciseId-1000)
            exercises += exercise
        }

        val repetitions = mutableListOf<Int>();
        val repetitionsString = workoutRepetitions.toString()
        val repetitionStrings = repetitionsString?.split(",");
        repetitionStrings?.forEach { repetition -> repetitions += repetition.toInt()}

        val tagIds = mutableListOf<Int>();
        val tagIdsString = workoutTagIds.toString()
        val tagIdsStrings = tagIdsString?.split(",");
        tagIdsStrings?.forEach { tagId -> tagIds += tagId.toInt()}
        tagIds.forEach {
                tagId ->
            var tag: TagDb = mTagViewModel.getById(tagId)
            tags += tag
        }


        if(!exercises.isEmpty()){
            exercises.forEach{exercise ->
                var workoutListLayout: LinearLayout = findViewById(R.id.exerciseLayout);
                var workoutExerciseRepetitionLayout: LinearLayout = findViewById(R.id.repetitionLayout);
                var exerciseLayout: LinearLayout = LinearLayout(this);
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
                val exerciseTextView = TextView(this);
                val repetitionTextView = TextView(this);
                exerciseTextView.layoutParams = params;
                repetitionTextView.layoutParams = params;
                exerciseTextView.setTextColor(Color.WHITE);
                repetitionTextView.setTextColor(Color.WHITE);

                exerciseTextView.text = exercise.title
                repetitionTextView.text = repetitions[0].toString();
                exerciseLayout.addView(exerciseTextView);
                workoutExerciseRepetitionLayout.addView(repetitionTextView);
                workoutListLayout.addView(exerciseLayout);
                repetitions.drop(1);
            }
        }
    }

}