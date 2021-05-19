package com.example.sweat4success.DatabaseTests

import android.os.Build
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sweat4success.database.AppDatabase
import com.example.sweat4success.database.UserDao
import com.example.sweat4success.database.UserDb
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
class UserTest {

    private var userDao: UserDao? = null
    private var db: AppDatabase? = null
    var user: UserDb = UserDb(1,"TestUser","TestPasswort", "test@gmai.com", 20, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,"","","","")

    @Before
    fun onCreateDb() = runBlocking{
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        userDao = db!!.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    fun testGetUserByName() = runBlocking{
        userDao?.addUser(user)
        val foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString())
        Assert.assertEquals(user, foundUser)

        var a = userDao?.delete(user)
    }

    @Test
    fun testGetUserById() = runBlocking{
        userDao?.addUser(user)
        val foundUser: UserDb? = userDao?.findById(user.uid)
        Assert.assertEquals(user, foundUser)

        var a = userDao?.delete(user)
    }

    @Test
    fun testDeleteUser() = runBlocking{
        userDao?.addUser(user)
        userDao?.delete(user)
        val foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString())
        Assert.assertNull(foundUser)

    }
    @Test
    fun testUpdateUser()= runBlocking{
        userDao?.addUser(user)
        val editedUser: UserDb = user
        editedUser.username = "EditedName"
        userDao?.updateUser(editedUser)
        val foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString())
        Assert.assertEquals(editedUser, foundUser)

        var a = userDao?.delete(editedUser)
    }
    @Test
    fun testCreateUser() = runBlocking{
        userDao?.addUser(user)
        val foundUser: UserDb? = userDao?.findByName(user.username.toString(), user.password.toString())
        Assert.assertEquals(user, foundUser)

        var a = userDao?.delete(user)
    }
}