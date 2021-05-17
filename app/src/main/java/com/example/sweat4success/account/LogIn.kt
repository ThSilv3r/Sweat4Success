package com.example.sweat4success.account;

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sweat4success.R
import com.example.sweat4success.Workout_Categories
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import com.example.sweat4success.modell.Account
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.drawer_layout
import kotlinx.android.synthetic.main.login.toolbar


public class LogIn : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mUserViewModel: UserViewModel;
    private var account: Account = Account();
    private var userList = listOf <UserDb>();
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.getUser();

        button.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java));
        }

        logInButton.setOnClickListener {
            checkDataInDatabase(userList);
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
        val userDao = AppDatabase.getDatabase(application).userDao();
        userList  = userDao.loadAll();
    }

    private fun checkDataInDatabase(userList:List<UserDb>) {
        var username: String = userNameTextBox.text.toString();
        var password: String = passwordTextBox.text.toString();
        account.setUsername(username);
        account.setUserList(userList)
        var a = account.getUserList();

            try {
                var user = mUserViewModel.findByName(username, password)

            }catch (e: IOException){
                throw e
                Toast.makeText(this, "Login failed, please enter the right password and username!", Toast.LENGTH_LONG).show();
            }
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
