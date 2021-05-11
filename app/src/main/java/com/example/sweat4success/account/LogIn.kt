package com.example.sweat4success.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import com.example.sweat4success.modell.Account


class LogIn : AppCompatActivity(){
    private lateinit var mUserViewModel: UserViewModel
    private var account: Account = Account()
    private var userList = listOf <UserDb>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        this.getUser()

        button.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }

        logInButton.setOnClickListener {
            checkDataInDatabase(userList)
        }
    }

    private fun getUser(){
        val userDao = AppDatabase.getDatabase(application).userDao()
        userList  = userDao.loadAll()
    }

    private fun checkDataInDatabase(userList:List<UserDb>) {
        var username: String = userNameTextBox.text.toString()
        var password: String = passwordTextBox.text.toString()
        account.setUsername(username)
        account.setUserList(userList)
        var a = account.getUserList()

        try {
                var user = userList.find{it.username == username && it.password == password}

            }catch (e: IOException){
                Toast.makeText(this, "Login failed, please enter the right password and username!", Toast.LENGTH_LONG).show()
            }
            startActivity(Intent(this, EditAccount::class.java))

    }

}
