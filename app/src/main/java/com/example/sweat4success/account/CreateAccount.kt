package com.example.sweat4success.account;

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.room.Insert
import com.example.sweat4success.R;
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.UserViewModel
import kotlinx.android.synthetic.main.createaccount.*
import java.io.IOException
import java.lang.Exception

public class CreateAccount: AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        val userDao = AppDatabase.getDatabase(application).userDao();

        var userList: List<UserDb>
        val thread = Thread{
            userList  = userDao.loadAll();
        }
        thread.start()

            createAccountButton.setOnClickListener {
                insertDataToDatabase();
            }

    }

    private fun insertDataToDatabase() {
        var username: String = userNameTextBoxC.text.toString();
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
            var user: UserDb = UserDb(0, username, password, email, age, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0,0,0);
            try {
                mUserViewModel.addUser(user);
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
}
