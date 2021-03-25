package com.example.sweat4success.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TagDb::class], version = 1, exportSchema = false)
abstract class TagDataBase : RoomDatabase(){
    abstract fun tagDao(): TagDao

    companion object{
        @Volatile
        private var INSTANCE: TagDataBase? = null;
        fun getDatabase(context: Application): TagDataBase{
            val tempInstance = INSTANCE;
            if(tempInstance != null){
                return tempInstance;
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TagDataBase::class.java,
                    "TagDb"
                ).build();
                INSTANCE = instance;
                return instance;
            }
        }


    }
}