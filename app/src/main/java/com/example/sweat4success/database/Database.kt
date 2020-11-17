package com.example.sweat4success.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import java.sql.Time


@Entity(tableName = "UserDb")
data class UserDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "bmi") val bmi: Double?,
    @ColumnInfo(name = "weight") val weight: Double?,
    @ColumnInfo(name = "height") val height: Double?,
    @ColumnInfo(name = "stomacheWidth") val stomacheWidth: Double?,
    @ColumnInfo(name = "bicepsWidth") val bicepsWidth: Double?,
    @ColumnInfo(name = "chestWidth") val chestWidth: Double?,
    @ColumnInfo(name = "quadWidth") val quadWidth: Double?,
    @ColumnInfo(name = "friendId") val friendId: Int?,
    @ColumnInfo(name = "workoutId") val workoutId: Int?,
    @ColumnInfo(name = "favoritesId") val favoritesId: Int?
    )




@Entity
data class WorkoutDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "duration") val duration: Time?,
    @ColumnInfo(name = "tagId") val tagId: Int?
)

@Entity
data class TagDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?
)

@Entity
data class ExerciseDb(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "pictureURL") val pictureURL: String?,
    @ColumnInfo(name = "description") val description: String?

    )


