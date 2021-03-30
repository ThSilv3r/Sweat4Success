package com.example.sweat4success.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.room.*
import java.sql.Time


@Entity(tableName = "UserDb")
data class UserDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "age") var age: Int?,
    @ColumnInfo(name = "bmi") val bmi: Double?,
    @ColumnInfo(name = "weight") var weight: Double?,
    @ColumnInfo(name = "height") var height: Double?,
    @ColumnInfo(name = "stomacheWidth") var stomacheWidth: Double?,
    @ColumnInfo(name = "bicepsWidth") var bicepsWidth: Double?,
    @ColumnInfo(name = "chestWidth") val chestWidth: Double?,
    @ColumnInfo(name = "quadWidth") val quadWidth: Double?,
    @ColumnInfo(name = "friendId") val friendId: Int?,
    @ColumnInfo(name = "workoutId") val workoutId: Int?,
    @ColumnInfo(name = "favoritesId") val favoritesId: Int?
    )




@Entity(tableName = "WorkoutDb")
data class WorkoutDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "duration") val duration: Int?,
    @ColumnInfo(name = "tagIds") val tagIds: String?,
    @ColumnInfo(name = "exerciseIds") val exerciseIds: String?,
    @ColumnInfo(name = "userId") val userId: Int?
)

@Entity(tableName = "TagDb")
data class TagDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") var name: String?
)

@Entity(tableName = "ExerciseDb")
data class ExerciseDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "pictureURL") val pictureURL: String?,
    @ColumnInfo(name = "description") val description: String?
    )
