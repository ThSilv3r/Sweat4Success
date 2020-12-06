package com.example.sweat4success.account;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room.databaseBuilder
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.UserViewModel
import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.android.synthetic.main.delete.*import kotlinx.android.synthetic.main.createaccount.*
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import com.example.sweat4success.modell.Account
import kotlinx.android.synthetic.main.createaccount.*


public class LogIn : AppCompatActivity(){
    private lateinit var mUserViewModel: UserViewModel;
    private var account: Account = Account();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        val userDao = AppDatabase.getDatabase(application).userDao();

        var userList = listOf <UserDb>()
        val thread = Thread{
            userList  = userDao.loadAll();
        }
        thread.start()

        button.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java));

        }

        logInButton.setOnClickListener {
            //startActivity(Intent(this, UserController::class.java));
            checkDataInDatabase(userList);
        }
        //var userName: String = userNameTextBox.text.toString();
        //var password: String = passwordTextBox.text.toString();
    }

    private fun checkDataInDatabase(userList:List<UserDb>) {
        var username: String = userNameTextBox.text.toString();
        var password: String = passwordTextBox.text.toString();
        account.setUsername(username);

            try {
                var user = userList.find{it.username == username && it.password == password};

            }catch (e: IOException){
                throw e
                Toast.makeText(this, "Login failed, please enter the right password and username!", Toast.LENGTH_LONG).show();
            }
            startActivity(Intent(this, EditAccount::class.java));

    }

}
