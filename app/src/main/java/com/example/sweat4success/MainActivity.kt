package com.example.sweat4success

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.*
import com.example.sweat4success.friend.FriendList
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView :NavigationView

    //lateinit var toggle: ActionBarDrawerToggle
    private var account: Account = Account()
    private var tag1: Tag = Tag()
    private var exercise1: Exercise  =  Exercise()
    private lateinit var tagViewModel: TagViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getDatabaseItems()

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.workouts_icon)
        setSupportActionBar(toolbar)



        /*toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation clicked", Toast.LENGTH_SHORT).show()
        }*/

        drawerLayout = findViewById(R.id.drawer_layout)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        /*toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/



        goToLogin.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
        }
        goToCreate.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Profile", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, ContactsContract.Profile::class.java))
            }
            R.id.nav_friends -> {
                startActivity(Intent(this, FriendList::class.java))
                Toast.makeText(
                    baseContext,
                    "Friends",
                    Toast.LENGTH_SHORT
                ).show()
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

                Toast.makeText(applicationContext, "Clicked Item 1", Toast.LENGTH_SHORT).show()
            }*/
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){

        }
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun getDatabaseItems() {
        val userDao = AppDatabase.getDatabase(application).userDao()
        val tagdao = TagDataBase.getDatabase(application).tagDao()
        val exerciseDao = ExerciseDataBase.getDatabase(application).exerciseDao()
        tagViewModel = ViewModelProvider(this).get(TagViewModel::class.java)
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        var tag = TagDb(0, "Bizeps")
        tagViewModel.addTag(tag)

        var exercise = ExerciseDb(0, "Liegestütze", "","",0)
        exerciseViewModel.addExercise(exercise)

        var taglist = listOf<TagDb>()
        taglist  = tagdao.loadAll()
        tag1.setTagList(taglist)

        var exerciseList = listOf<ExerciseDb>()
        exerciseList = exerciseDao.loadAll()
        exercise1.setExerciseList(exerciseList)

        var userList = listOf<UserDb>()
        userList = userDao.loadAll()
        account.setUserList(userList)

    }
}