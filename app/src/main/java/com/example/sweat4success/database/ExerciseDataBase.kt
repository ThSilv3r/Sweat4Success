package com.example.sweat4success.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExerciseDb::class], version = 1, exportSchema = false)
abstract class ExerciseDataBase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao;

    companion object{
        @Volatile
        private var INSTANCE: ExerciseDataBase? = null;
        fun getDatabase(context: Application): ExerciseDataBase{
            val tempInstance = INSTANCE;
            if(tempInstance != null){
                return tempInstance;
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDataBase::class.java,
                    "ExerciseDb"
                ).build();
                INSTANCE = instance;
                return instance;
            }
        }


    }
}