package com.example.sweat4success.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null;
        fun getDatabase(context: Application): AppDatabase{
            val tempInstance = INSTANCE;
            if(tempInstance != null){
                return tempInstance;
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "UserDb"
                ).build();
                INSTANCE = instance;
                return instance;
            }
        }


    }
}