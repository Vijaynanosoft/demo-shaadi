package com.shaadi.assignment.data.local.db.typeconverters

import androidx.room.TypeConverter

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun toStatus(value: Int) = enumValues<UserStatus>()[value]

    @TypeConverter
    @JvmStatic
    fun fromStatus(value: UserStatus) = value.ordinal

}