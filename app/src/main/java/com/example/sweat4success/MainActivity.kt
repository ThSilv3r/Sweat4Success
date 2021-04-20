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
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.*
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.TagViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView :NavigationView

    //lateinit var toggle: ActionBarDrawerToggle
    private var account: Account = Account();
    private lateinit var tagViewModel: TagViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getUser();

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.workouts_icon)
        setSupportActionBar(toolbar)



        /*toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation clicked", Toast.LENGTH_SHORT).show()
        }*/

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
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
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(baseContext, "Clicked profile", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> Toast.makeText(
                baseContext,
                "Clicked Friends",
                Toast.LENGTH_SHORT
            ).show()
            /*R.id.nav_workouts -> {
                wor = Workouts()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id., workout)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

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

    private fun getUser(){
        val userDao = AppDatabase.getDatabase(application).userDao();
        var userList = listOf<UserDb>();

        val thread = Thread{
            userList  = userDao.loadAll();
            account.setUserList(userList);
        }
        thread.start()
    }




}