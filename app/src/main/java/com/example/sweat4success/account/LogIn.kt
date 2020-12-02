package com.example.sweat4success.account;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*

public class LogIn : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        button.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java));
        }

        logInButton.setOnClickListener {
            startActivity(Intent(this, UserController::class.java));
        }
        var userName: String = userNameTextBox.text.toString();
        var password: String = passwordTextBox.text.toString();
    }
}
