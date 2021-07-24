package com.shaadi.assignment.data.repository

import androidx.lifecycle.LiveData
import com.shaadi.assignment.data.local.db.DatabaseService
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import com.shaadi.assignment.data.remote.NetworkService
import com.shaadi.assignment.utils.Utils
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchUserList(count: String): Single<List<User>> {
        val data = networkService.getUserData(count).map {
            Utils.convertList(it.results)
        }
        return data
    }

    fun saveUserList(list: List<User>): Single<List<Long>> {
        return databaseService.userDao().insert(list)
    }

    fun getAll(): LiveData<List<User>> =
        databaseService.userDao().getAll()


    fun updateStatus(userStatus: UserStatus, id: Long) =
        databaseService.userDao().updateStatus(userStatus, id)

}

