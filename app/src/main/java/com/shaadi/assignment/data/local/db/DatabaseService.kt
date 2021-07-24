package com.shaadi.assignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaadi.assignment.data.local.db.dao.UserDao
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.RoomConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        User::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun userDao(): UserDao

}