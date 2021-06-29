package com.example.sweat4success.UnitTests

import android.os.Build
import com.example.sweat4success.database.WorkoutDb
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class WorkoutUnitTest {
    lateinit var workout:WorkoutDb

    @Before
    fun initWorkout(){
        createUser("test", "test",19)
    }
    @Test
    fun title_isCorect(){
        Assert.assertEquals("test", workout.title)
        createUser("test2","test",19)
        Assert.assertEquals("test2", workout.title)
    }

    fun createUser(name: String, description: String, duration: Int){
        workout = WorkoutDb(0, name, description,duration,"","",0,"")
    }
}