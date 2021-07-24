package com.shaadi.assignment.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long,

    @ColumnInfo(name = "name")
    @NotNull
    val name: String,

    @ColumnInfo(name = "age")
    @NotNull
    val age: Int,

    @ColumnInfo(name = "imgUrl")
    @NotNull
    val imgUrl: String,

    @ColumnInfo(name = "status")
    @NotNull
    val user_status: UserStatus

)