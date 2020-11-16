package com.example.sweat4success.account;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Insert
import com.example.sweat4success.R;
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.UserViewModel
import kotlinx.android.synthetic.main.createaccount.*
import java.lang.Exception

public class CreateAccount: AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

            createAccountButton.setOnClickListener {
                insertDataToDatabase()
            }

    }

    private fun insertDataToDatabase() {
        var username: String = userNameTextBoxC.text.toString();
        var password: String = passwordTextBoxC.text.toString();
        var email: String = emailTextBoxC.text.toString();
        var age: Int = 0;
        //var account: account = account(username, password, email, 0.0, 0.0, 0.0, age);

        var user: UserDb = UserDb(0, username, password, email, age, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0,0,0);
        mUserViewModel.addUser(user);
    }
}
