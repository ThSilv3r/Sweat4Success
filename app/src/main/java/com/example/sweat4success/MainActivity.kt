package com.example.sweat4success

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.*
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Exercise
import com.example.sweat4success.modell.Tag
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView :NavigationView

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

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.workouts_icon)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)




        goToLogin.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
        }
        goToCreate.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Clicked profile", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> Toast.makeText(
                baseContext,
                "Clicked Friends",
                Toast.LENGTH_SHORT
            ).show()

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

        userList  = userDao.loadAll();
        exerciseList = exerciseDao.loadAll();
        tagList =  tagDao.loadAll();
        account.setUserList(userList);
        exercise.setExerciseList(exerciseList);
        tag.setTagList(tagList);
    }
}