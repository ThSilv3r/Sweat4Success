package com.example.sweat4success.account

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.editaccount.*
import kotlinx.android.synthetic.main.editaccount.drawer_layout


class EditAccount: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var account: Account = Account();
    private lateinit var mUserViewModel: UserViewModel;
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        this.setUITexts()

        deleteAccountButton.setOnClickListener {
            startActivity(Intent(this, UserController::class.java));
        }

        editaccount.setOnClickListener {
            var userList = account.getUserList();
            var username: String = account.getUsername();
            var user = userList.find{it.username == username}as UserDb;
            updateUser(user)
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java));
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

    private fun setUITexts(){
        var userList = account.getUserList();
        var username: String = account.getUsername();
        var password: String = account.getPassword()
        var user = mUserViewModel.findByName(username, password);

        var age = user.age.toString();
        var height = user.height.toString();
        var biceps = user.bicepsWidth.toString();
        var weight = user.weight.toString();
        var stomache = user.stomacheWidth.toString();
        dateOfBirthTextBoxeditacc.setText(age as CharSequence)
        userNameTextBoxeditacc.setText(user.username as CharSequence)
        heightTextBoxeditacc.setText(height as CharSequence)
        weightTextBoxeditacc.setText(weight as CharSequence)
        bizepsTextBoxeditacc.setText(biceps as CharSequence)
        waistTextBoxeditacc.setText(stomache as CharSequence)
    }


    private fun updateUser(user: UserDb){
        var age: Int = dateOfBirthTextBoxeditacc.text.toString().toInt()
        var height: Double = heightTextBoxeditacc.text.toString().toDouble()
        var weight: Double = weightTextBoxeditacc.text.toString().toDouble()
        var bizeps: Double = bizepsTextBoxeditacc.text.toString().toDouble()
        var waist: Double = waistTextBoxeditacc.text.toString().toDouble()

        user.age = age
        user.height = height
        user.weight = weight
        user.bicepsWidth = bizeps
        user.stomacheWidth = waist

        mUserViewModel.updateUser(user)
        startActivity(Intent(this, EditAccount::class.java));
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
