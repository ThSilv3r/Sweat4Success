package com.example.sweat4success.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.delete.*


class DeleteAccount: AppCompatActivity(){
    private lateinit var mUserViewModel: UserViewModel
    private var userList = listOf<UserDb>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        this.getUsers()

        deleteButton.setOnClickListener {
            deleteDataFromDatabase(userList)
        }

    }
    private fun getUsers(){
        val userDao = AppDatabase.getDatabase(application).userDao()
        userList  = userDao.loadAll()
    }

    private fun deleteDataFromDatabase(userList: List<UserDb>) {
        var username: String = usernameTextBoxD.text.toString()
        var password: String = passwordTextBoxD.text.toString()

        if(inputCheck(username, password)){

            var user: UserDb = userList.find { it.username == username && it.password ==  password} as UserDb
            mUserViewModel.deleteUser(user)


            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Succesfully deleted account!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(username: String, password: String): Boolean {
        return !(username == "" && password == "")
    }

}