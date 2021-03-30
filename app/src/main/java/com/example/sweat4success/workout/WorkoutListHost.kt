package com.example.sweat4success.workout

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sweat4success.R

class WorkoutListHost: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_list_host)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }
}