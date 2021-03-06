package com.example.sweat4success

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sweat4success.BaseActivity
import com.example.sweat4success.R
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.workout.WorkoutList
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.drawer_layout

class Workout_Categories:AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView
    private var workoutModel =  Workouts()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_categories)


        // register all the card views with their appropriate IDs
        val armsCard: CardView = findViewById(R.id.armsCard)
        val stretchingCard: CardView = findViewById(R.id.stretchingCard)
        val cardioCard: CardView = findViewById(R.id.cardioCard)
        val absCard: CardView = findViewById(R.id.absCard)
        val weightCard: CardView = findViewById(R.id.weightsCard)
        val legsCard: CardView = findViewById(R.id.legsCard)




        // handle each of the cards with the OnClickListener
        armsCard.setOnClickListener {
            workoutModel.setWorkoutTag(1)
            Toast.makeText(this, "Arms", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
            //startActivity(Intent(this, arms_Card::class.java))
        }
        stretchingCard.setOnClickListener {
            workoutModel.setWorkoutTag(3)
            Toast.makeText(this, "Stretching", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
            //startActivity(Intent(this, stretching_Card::class.java))
        }
        cardioCard.setOnClickListener {
            workoutModel.setWorkoutTag(2)
            Toast.makeText(this, "Cardio", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
        }
        absCard.setOnClickListener {
            workoutModel.setWorkoutTag(4)
            Toast.makeText(this, "Abs", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
        }
        weightCard.setOnClickListener {
            workoutModel.setWorkoutTag(6)
            Toast.makeText(this, "Weights", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
        }
        legsCard.setOnClickListener {
            workoutModel.setWorkoutTag(5)
            Toast.makeText(this, "Legs", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WorkoutList::class.java))
        }

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.workouts_icon)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Profile", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, ContactsContract.Profile::class.java))
            }
            R.id.nav_friends -> Toast.makeText(
                baseContext,
                "Friends",
                Toast.LENGTH_SHORT
            ).show()
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
}