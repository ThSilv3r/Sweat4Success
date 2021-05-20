package com.example.sweat4success.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.createaccount.*
import java.io.IOException

class CreateAccount: AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    private var account: Account = Account()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        Login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
        createAccountButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val username: String = userNameTextBoxC.text.toString()
        account.setUsername(username)
        var userList = account.getUserList()

        val password: String = passwordTextBoxC.text.toString()
        val email: String = emailTextBoxC.text.toString()
        val age: Int
        if (ageTextBoxC.text.toString() != "") {
            age = Integer.parseInt(ageTextBoxC.text.toString())
        } else {
            age = 0
        }

        if (inputCheck(username, password, email)) {
            var user = UserDb(0, username, password, email, age,0.0,0.0,0.0,0.0,0.0,0.0,0.0,"","","","")
            try {
                mUserViewModel.addUser(user)
                user = mUserViewModel.findByName(user.username.toString(), user.password.toString())
                userList += user
                account.setUserList(userList)
                account.setUsername(username)
                account.setPassword(password)
            } catch (e: IOException) {
            }
            startActivity(Intent(this, EditAccount::class.java))
            Toast.makeText(this, "Succesfully created account!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(username: String, password: String, email: String): Boolean {
        return !(username == "" && password == "" && email == "")
    }
}
