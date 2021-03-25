package com.example.sweat4success.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WorkoutDb::class], version = 1, exportSchema = false)
abstract class WorkoutDataBase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object{
        @Volatile
        private var INSTANCE: WorkoutDataBase? = null;
        fun getDatabase(context: Application): WorkoutDataBase{
            val tempInstance = INSTANCE;
            if(tempInstance != null){
                return tempInstance;
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDataBase::class.java,
                    "WorkoutDb"
                ).build();
                INSTANCE = instance;
                return instance;
            }
        }


    }
}