package com.example.sweat4success.account

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room.databaseBuilder
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.UserViewModel
import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.android.synthetic.main.delete.*
import java.io.IOException


public class UserController: AppCompatActivity(){
    private lateinit var mUserViewModel: UserViewModel;

    private var account: Account = Account();



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        val userDao = AppDatabase.getDatabase(application).userDao();

        var username: String = "";
        var account = account;

        username = account.getUsername();


        var userList = listOf<UserDb>();
        val thread = Thread{
            userList  = userDao.loadAll();
        }
        thread.start()

        deleteButton.setOnClickListener {
            deleteDataFromDatabase(userList);
        }

    }

    private fun deleteDataFromDatabase(userList: List<UserDb>) {
        var username: String = usernameTextBoxD.text.toString();
        var password: String = passwordTextBoxD.text.toString();
        var age: Int;


        if(inputCheck(username, password)){

            var user: UserDb = userList.find { it.username == username && it.password ==  password} as UserDb;
            mUserViewModel.deleteUser(user);


            startActivity(Intent(this, MainActivity::class.java));
            Toast.makeText(this, "Succesfully created account!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show();
        }
    }
    private fun inputCheck(username: String, password: String): Boolean {
        return !(username == "" && password == "")
    }

    fun logOut(){

    }

    fun DeleteAccount(){

    }
}