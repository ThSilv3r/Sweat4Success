package com.example.sweat4success.DatabaseTests

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
class ExerciseTests {
    private var exerciseDao: ExerciseDao? = null
    private var db: ExerciseDataBase? = null
    var exercise: ExerciseDb = ExerciseDb(1,"TestUser", "","",1)

    @Before
    fun onCreateDb() = runBlocking{
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, ExerciseDataBase::class.java).allowMainThreadQueries().build()
        exerciseDao = db!!.exerciseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    fun testGetExercise() = runBlocking{
        exerciseDao?.addExercise(exercise)
        val foundExercise = exerciseDao?.findById(exercise.uid)
        Assert.assertEquals(exercise, foundExercise)

        var a = exerciseDao?.delete(exercise)
    }

    @Test
    fun testAddExercise() = runBlocking{
        exerciseDao?.addExercise(exercise)
        val foundExercise = exerciseDao?.findById(exercise.uid)
        Assert.assertEquals(exercise, foundExercise)

        var a = exerciseDao?.delete(exercise)
    }

    @Test
    fun testDelteExercise() = runBlocking{
        exerciseDao?.addExercise(exercise)
        var a = exerciseDao?.delete(exercise)
        val foundExercise = exerciseDao?.findById(exercise.uid)
        Assert.assertNull(foundExercise)
    }
    @Test
    fun testUpdateExercise() = runBlocking{
        exerciseDao?.addExercise(exercise)
        val updatedExercies = exercise
        updatedExercies.title = "updatedExercies"
        exerciseDao?.updateExercise(updatedExercies)
        val foundExercise = exerciseDao?.findById(exercise.uid)
        Assert.assertEquals(foundExercise, updatedExercies)
    }

    @Test
    fun testLoadAllExercises() = runBlocking {
        exerciseDao?.addExercise(exercise)
        val exercises = exerciseDao?.loadAll()
        Assert.assertNotNull(exercises)
    }
}