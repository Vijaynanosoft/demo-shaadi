package com.shaadi.assignment.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert
    fun insert(entity: User): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<User>): Single<List<Long>>

    @Delete
    fun delete(entity: User): Single<Int>

    @Query("UPDATE user SET status=:userStatus WHERE id=:id")
    fun updateStatus(userStatus: UserStatus, id: Long): Single<Int>

}