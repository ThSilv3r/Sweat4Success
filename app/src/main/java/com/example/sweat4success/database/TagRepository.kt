package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class TagRepository (private val tagDao: TagDao){

    val readAllData: LiveData<List<TagDb>> = tagDao.readAllData()

    public fun getAll(){
        tagDao.loadAll()
    }

    public suspend fun addTag(tag:TagDb){
        tagDao.addTag(tag)
    }

    public fun findById(id: Int): TagDb {
        return tagDao.findById(id)
    }

    public fun delete(tag: TagDb){
        tagDao.delete(tag)
    }


}