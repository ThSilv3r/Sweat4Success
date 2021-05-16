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
import com.example.sweat4success.BaseActivity
import com.example.sweat4success.R;
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.createaccount.*
import java.io.IOException

public class CreateAccount: AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mUserViewModel: UserViewModel;
    private var account: Account = Account();
    private var userList = listOf <UserDb>();
    private var base: BaseActivity = BaseActivity();
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

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


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        this.getUser();

        Login.setOnClickListener {
            startActivity(Intent(this, Login::class.java));
        }

            createAccountButton.setOnClickListener {
                insertDataToDatabase(userList);
            }

    }
    private fun getUser(){
        val userDao = AppDatabase.getDatabase(application).userDao();

        userList  = userDao.loadAll();
    }


    private fun insertDataToDatabase(userlist:List<UserDb>) {
        var username: String = userNameTextBoxC.text.toString();
        account.setUsername(username);
        account.setUserList(userlist);
        var userList = account.getUserList();


        var password: String = passwordTextBoxC.text.toString();
        var email: String = emailTextBoxC.text.toString();
        var age: Int;
        if(ageTextBoxC.text.toString() != ""){
            age = Integer.parseInt(ageTextBoxC.text.toString());
        }else{
            age = 0;
        }
        //var account: account = account(username, password, email, 0.0, 0.0, 0.0, age);

        if(inputCheck(username, password, email)){
            var user: UserDb = UserDb(0, username, password, email, age, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,"0","0","0");
            try {
                mUserViewModel.addUser(user);
                userList += user;
                account.setUserList(userList);
            }catch (e: IOException){
                throw e;
            }
            startActivity(Intent(this, EditAccount::class.java));
            Toast.makeText(this, "Succesfully created account!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show();
        }
    }


    private fun inputCheck(username: String, password: String, email: String): Boolean {
        return !(username == "" && password == "" && email=="")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(this, "Clicked profile", Toast.LENGTH_SHORT).show()
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



}
