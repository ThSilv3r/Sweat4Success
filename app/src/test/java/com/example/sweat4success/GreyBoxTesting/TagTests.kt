package com.example.sweat4success.GreyBoxTesting

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
class TagTests {
    private var tagDao: TagDao? = null
    private var db: TagDataBase? = null
    var tag: TagDb = TagDb(1,"TestUser")

    @Before
    fun onCreateDb() = runBlocking{
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, TagDataBase::class.java).allowMainThreadQueries().build()
        tagDao = db!!.tagDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    fun testGetTag() = runBlocking{
        tagDao?.addTag(tag)
        val foundTag = tagDao?.findById(tag.uid)
        Assert.assertEquals(tag, foundTag)

        var a = tagDao?.delete(tag)
    }

    @Test
    fun testAddTag() = runBlocking{
        tagDao?.addTag(tag)
        val foundTag = tagDao?.findById(tag.uid)
        Assert.assertEquals(tag, foundTag)

        var a = tagDao?.delete(tag)
    }

    @Test
    fun testDelteTag() = runBlocking{
        tagDao?.addTag(tag)
        var a = tagDao?.delete(tag)
        val foundTag = tagDao?.findById(tag.uid)
        Assert.assertNull(foundTag)
    }
    @Test
    fun testLoadAllTags() = runBlocking{
        tagDao?.addTag(tag)
        val tags = tagDao?.loadAll()
        Assert.assertNotNull(tags)
    }

}