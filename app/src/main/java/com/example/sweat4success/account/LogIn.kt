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
import androidx.lifecycle.ViewModelProvider
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
import java.lang.Exception


public class LogIn : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mUserViewModel: UserViewModel;
    private var account: Account = Account();
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        button.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java));
        }

        logInButton.setOnClickListener {
            checkDataInDatabase();
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

    private fun checkDataInDatabase() {
        var username: String = userNameTextBox.text.toString();
        var password: String = passwordTextBox.text.toString();
        account.setUsername(username);
        account.setPassword(password)

            try {
                var user = mUserViewModel.findByName(username, password)
                if(user != null){
                    startActivity(Intent(this, EditAccount::class.java));
                }
            }catch (e: Exception){
                Toast.makeText(this, "Login failed, please enter the right password and username!", Toast.LENGTH_LONG).show();
            }

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
