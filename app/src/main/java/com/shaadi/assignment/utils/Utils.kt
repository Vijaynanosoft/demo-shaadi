package com.shaadi.assignment.utils

import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import com.shaadi.assignment.data.remote.response.Result

object Utils {


    fun convertList(resultList: List<Result>): List<User> {

        val userList = mutableListOf<User>()

        resultList.forEach {
            userList.add(
                User(
                    0,
                    it.name.first + " " + it.name.last[0],
                    it.dob.age,
                    it.picture.large,
                    UserStatus.PENDING
                )
            )
        }

        return userList
    }

}