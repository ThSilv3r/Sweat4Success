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
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.friends.FriendList
import com.example.sweat4success.friends.Userprofile
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.workout.ShareWorkout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.android.synthetic.main.createaccount.drawer_layout
import kotlinx.android.synthetic.main.delete.*
import java.io.IOException

class CreateAccount: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mUserViewModel: UserViewModel
    private var account: Account = Account()
    private var userList = listOf <UserDb>()
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        this.getUser()

        Login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

            createAccountButton.setOnClickListener {
                insertDataToDatabase(userList)
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
    private fun getUser(){
        val userDao = AppDatabase.getDatabase(application).userDao()

        userList  = userDao.loadAll()
    }


    private fun insertDataToDatabase(userlist:List<UserDb>) {
        val username: String = userNameTextBoxC.text.toString()
        account.setUsername(username)
        account.setUserList(userlist)
        val userList = account.getUserList().toMutableList()


        val password: String = passwordTextBoxC.text.toString()
        val email: String = emailTextBoxC.text.toString()
        val age: Int
        if(ageTextBoxC.text.toString() != ""){
            age = Integer.parseInt(ageTextBoxC.text.toString())
        }else{
            age = 0
        }

        if(inputCheck(username, password, email)){
            var user = UserDb(0, username, password, email, age, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,"","","","")
            try {
                mUserViewModel.addUser(user)
                user = mUserViewModel.findByName(user.username.toString(), user.password.toString())
                userList += user
                account.setUserList(userList)
                account.setUsername(username)
                account.setPassword(password)
            }catch (e: IOException){
            }
            startActivity(Intent(this, EditAccount::class.java))
            Toast.makeText(this, "Succesfully created account!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(username: String, password: String, email: String): Boolean {
        return !(username == "" && password == "" && email=="")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Profile", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Userprofile::class.java))
            }
            R.id.nav_friends ->{
                Toast.makeText(
                    baseContext,
                    "Friends",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, FriendList::class.java))
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
            R.id.nav_share -> {
                startActivity(Intent(this, ShareWorkout::class.java))
                Toast.makeText(
                    baseContext,
                    "Share Workout",
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
