package com.shaadi.assignment.data.remote

import com.shaadi.assignment.data.remote.response.UserDataResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("api")
    fun getUserData(
        @Query("results") resultsCount: String
    ): Single<UserDataResponse>


}