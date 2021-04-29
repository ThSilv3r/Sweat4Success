package com.example.sweat4success.UnitTests

import android.os.Build
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sweat4success.database.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class WorkoutTests {

    private var workoutDao: WorkoutDao? = null;
    private var db: WorkoutDataBase? = null;
    var workout: WorkoutDb = WorkoutDb(1,"TestUser","TestPasswort", 0, "", "",0,"");

    @Before
    fun onCreateDb() = runBlocking{
        val context = InstrumentationRegistry.getInstrumentation().context;
        db = Room.inMemoryDatabaseBuilder(context, WorkoutDataBase::class.java).allowMainThreadQueries().build();
        workoutDao = db!!.workoutDao();
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }
    @Test
    fun testGetWorkout() = runBlocking{
        workoutDao?.addWorkout(workout);
        var foundWorkout = workoutDao?.findById(workout.uid);
        Assert.assertEquals(workout, foundWorkout);

        var a = workoutDao?.delete(workout);
    }

    @Test
    fun testAddWorkout() = runBlocking{
        workoutDao?.addWorkout(workout);
        var foundWorkout = workoutDao?.findById(workout.uid);
        Assert.assertEquals(workout, foundWorkout);

        var a = workoutDao?.delete(workout);
    }

    @Test
    fun testDeleteWorkout() = runBlocking{
        workoutDao?.addWorkout(workout);
        var a = workoutDao?.delete(workout);
        var foundWorkout = workoutDao?.findById(workout.uid);
        Assert.assertNull(foundWorkout);
    }






}