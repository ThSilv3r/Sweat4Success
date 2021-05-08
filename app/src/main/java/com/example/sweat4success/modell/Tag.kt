package com.example.sweat4success.modell

import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.TagDb

class Tag {
        companion object{
                lateinit var tagList: List<TagDb>
        }
        var name: String = ""

        fun getTagList(): List<TagDb> {
                return Tag.tagList
        }

        fun setTagList(newTagList: List<TagDb>){
                tagList = newTagList
        }
}