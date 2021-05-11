package com.example.sweat4success.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.MainActivity
import com.example.sweat4success.R
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.workout.AddWorkout
import com.example.sweat4success.workout.ViewWorkout
import kotlinx.android.synthetic.main.editaccount.*


class EditAccount: AppCompatActivity() {
    private var account: Account = Account()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editaccount)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        this.setUITexts()

        deleteAccountButton.setOnClickListener {
            startActivity(Intent(this, DeleteAccount::class.java))
        }

        editaccount.setOnClickListener {
            var userList = account.getUserList()
            var username: String = account.getUsername()
            var user = userList.find{it.username == username}as UserDb
            updateUser(user)
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        testbutton.setOnClickListener {
            startActivity(Intent(this, ViewWorkout::class.java))
        }

    }

    private fun setUITexts(){
        var userList = account.getUserList()
        var username: String = account.getUsername()
        var password: String = account.getPassword()
        var user = mUserViewModel.findByName(username, password)as UserDb

        var age = user.age.toString()
        var height = user.height.toString()
        var biceps = user.bicepsWidth.toString()
        var weight = user.weight.toString()
        var stomache = user.stomacheWidth.toString()
        dateOfBirthTextBoxeditacc.setText(age as CharSequence)
        userNameTextBoxeditacc.setText(user.username as CharSequence)
        heightTextBoxeditacc.setText(height as CharSequence)
        weightTextBoxeditacc.setText(weight as CharSequence)
        bizepsTextBoxeditacc.setText(biceps as CharSequence)
        waistTextBoxeditacc.setText(stomache as CharSequence)
    }


    private fun updateUser(user: UserDb){
        var age: Int = dateOfBirthTextBoxeditacc.text.toString().toInt()
        var height: Double = heightTextBoxeditacc.text.toString().toDouble()
        var weight: Double = weightTextBoxeditacc.text.toString().toDouble()
        var bizeps: Double = bizepsTextBoxeditacc.text.toString().toDouble()
        var waist: Double = waistTextBoxeditacc.text.toString().toDouble()

        user.age = age
        user.height = height
        user.weight = weight
        user.bicepsWidth = bizeps
        user.stomacheWidth = waist

        mUserViewModel.updateUser(user)
        startActivity(Intent(this, EditAccount::class.java))
    }
}
