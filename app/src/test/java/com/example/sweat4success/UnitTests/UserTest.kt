package com.example.sweat4success.UnitTests

import android.app.Activity
import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDao
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import java.io.IOException
import androidx.test.InstrumentationRegistry.getContext;
import androidx.test.platform.app.InstrumentationRegistry
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class UserTest {

    private var userDao: UserDao? = null;
    private var db: AppDatabase? = null;
    var user: UserDb = UserDb(1,"TestUser","TestPasswort", "test@gmai.com", 20, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,"","","");

    @Before
    fun onCreateDb() = runBlocking{
        val context = InstrumentationRegistry.getInstrumentation().context;
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build();
        userDao = db!!.userDao();
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    fun testGetUser() = runBlocking{
        userDao?.addUser(user);
        var foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString());
        Assert.assertEquals(user, foundUser);

        var a = userDao?.delete(user);
    }
    @Test
    fun testDeleteUser() = runBlocking{
        userDao?.addUser(user);
        userDao?.delete(user);
        var foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString());
        Assert.assertNull(foundUser);

    }
    @Test
    fun testUpdateUser()= runBlocking{
        userDao?.addUser(user)
        var editedUser: UserDb = user;
        editedUser.username = "EditedName";
        userDao?.updateUser(editedUser);
        var foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString());
        Assert.assertEquals(editedUser, foundUser);

        var a = userDao?.delete(editedUser);
    }
    @Test
    fun testCreateUser() = runBlocking{
        userDao?.addUser(user);
        var foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString());
        Assert.assertEquals(user, foundUser);

        var a = userDao?.delete(user);
    }
}