package com.shaadi.assignment.di.module

import androidx.room.Room
import com.shaadi.assignment.BuildConfig
import com.shaadi.assignment.ShaadiApplication
import com.shaadi.assignment.data.local.db.DatabaseService
import com.shaadi.assignment.data.remote.NetworkService
import com.shaadi.assignment.data.remote.Networking
import com.shaadi.assignment.utils.RxSchedulerProvider
import com.shaadi.assignment.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(val application: ShaadiApplication) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL
        )

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "shaadi-db"
        ).build()

}