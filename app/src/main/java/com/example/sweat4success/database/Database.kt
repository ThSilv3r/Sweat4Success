package com.example.sweat4success.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserDb")
data class UserDb(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "username") var username: String?,
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
    @ColumnInfo(name = "friendId") var friendId: String?,
    @ColumnInfo(name = "workoutId") var workoutId: String?,
    @ColumnInfo(name = "favoritesId") val favoritesId: String?,
    @ColumnInfo(name = "recievedWorkouts") var recievedWorkouts: String?
    )




@Entity(tableName = "WorkoutDb")
data class WorkoutDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "duration") val duration: Int?,
    @ColumnInfo(name = "tagIds") val tagIds: String?,
    @ColumnInfo(name = "exerciseIds") val exerciseIds: String?,
    @ColumnInfo(name = "userId") val userId: Int?,
    @ColumnInfo(name = "repetitions") val repetitions: String?
    )

@Entity(tableName = "TagDb")
data class TagDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") var name: String?
)

@Entity(tableName = "ExerciseDb")
data class ExerciseDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "pictureURL") val pictureURL: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "repetitions") val repetitions: Int?
    )
