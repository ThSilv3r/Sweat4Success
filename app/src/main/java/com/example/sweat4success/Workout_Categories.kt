package com.example.sweat4success

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sweat4success.BaseActivity
import com.example.sweat4success.R
import com.example.sweat4success.account.LogIn
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.viewModel.UserViewModel
import kotlinx.android.synthetic.main.login.*
import java.io.IOException
import com.example.sweat4success.modell.Account
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView

class Workout_Categories:AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_categories)


        // register all the card views with their appropriate IDs
        val armsCard: CardView = findViewById(R.id.armsCard)
        val stretchingCard: CardView = findViewById(R.id.stretchingCard)
        val cardioCard: CardView = findViewById(R.id.cardioCard)
        val absCard: CardView = findViewById(R.id.absCard)
        val weightCard: CardView = findViewById(R.id.weightsCard)
        val legsCard: CardView = findViewById(R.id.legsCard)




        // handle each of the cards with the OnClickListener
        armsCard.setOnClickListener {
            Toast.makeText(this, "Arms", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, arms_Card::class.java))
        }
        stretchingCard.setOnClickListener {
            Toast.makeText(this, "Stretching", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, stretching_Card::class.java))
        }
        cardioCard.setOnClickListener {
            Toast.makeText(this, "Cardio", Toast.LENGTH_SHORT).show()
        }
        absCard.setOnClickListener {
            Toast.makeText(this, "Abs", Toast.LENGTH_SHORT).show()
        }
        weightCard.setOnClickListener {
            Toast.makeText(this, "Weights", Toast.LENGTH_SHORT).show()
        }
        legsCard.setOnClickListener {
            Toast.makeText(this, "Legs", Toast.LENGTH_SHORT).show()
        }
    }
}