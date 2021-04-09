package com.example.sweat4success

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.*
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Exercise
import com.example.sweat4success.modell.Tag
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.workout.AddWorkout
import com.example.sweat4success.workout.ViewWorkout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private var account: Account = Account();
    private var tag: Tag = Tag();
    private var exercise: Exercise = Exercise();
    private lateinit var tagViewModel: TagViewModel;
    private lateinit var mExerciseViewModel: ExerciseViewModel;
    private lateinit var mTagViewModel: TagViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getDatabaseItems();


        goToLogin.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
        }
        goToCreate.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this, ViewWorkout::class.java))
        }
    }

    private fun getDatabaseItems(){
        val userDao = AppDatabase.getDatabase(application).userDao();
        val tagDao = TagDataBase.getDatabase(application).tagDao();
        val exerciseDao = ExerciseDataBase.getDatabase(application).exerciseDao();

        var exerciseList = listOf<ExerciseDb>();
        var tagList = listOf<TagDb>();
        var userList = listOf<UserDb>();
        mExerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java);
        mTagViewModel = ViewModelProvider(this).get(TagViewModel::class.java);
        var tagDb = TagDb(0, "Bizeps");
        mTagViewModel.addTag(tagDb);
        var exerciseDb: ExerciseDb = ExerciseDb(0,"Liegestütze", "", "",0);
        mExerciseViewModel.addExercise(exerciseDb);

        val thread = Thread{
            userList  = userDao.loadAll();
            exerciseList = exerciseDao.loadAll();
            tagList =  tagDao.loadAll();
            account.setUserList(userList);
            exercise.setExerciseList(exerciseList);
            tag.setTagList(tagList);
        }
        thread.start()
    }
}