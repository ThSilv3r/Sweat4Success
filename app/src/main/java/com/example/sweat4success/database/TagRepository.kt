package com.example.sweat4success.database

import androidx.lifecycle.LiveData

class TagRepository (private val tagDao: TagDao){

    val readAllData: LiveData<List<TagDb>> = tagDao.readAllData()

    fun getAll(){
        tagDao.loadAll()
    }

    suspend fun addTag(tag:TagDb){
        tagDao.addTag(tag)
    }

    fun findById(id: Int): TagDb {
        return tagDao.findById(id)
    }

    fun delete(tag: TagDb){
        tagDao.delete(tag)
    }


}