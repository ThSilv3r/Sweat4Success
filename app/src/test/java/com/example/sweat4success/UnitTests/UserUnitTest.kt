package com.example.sweat4success.UnitTests

import android.os.Build
import com.example.sweat4success.database.UserDb
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class UserUnitTest {
    lateinit var user: UserDb
    @Before
    fun initUser(){
        createUser("test", "test",19)
    }
    @Test
    fun username_isCorect(){
        Assert.assertEquals("test", user.username)
        createUser("test2","test",19)
        Assert.assertEquals("test2", user.username)
    }

    fun createUser(name: String,password: String, age: Int){
        user = UserDb(0, name, password,"", age, 0.0, 0.0,0.0,0.0,0.0,0.0,0.0,"","","","")
    }
}