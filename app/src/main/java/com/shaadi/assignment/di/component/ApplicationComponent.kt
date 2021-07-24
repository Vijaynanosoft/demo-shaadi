package com.shaadi.assignment.di.component

import com.shaadi.assignment.ShaadiApplication
import com.shaadi.assignment.data.local.db.DatabaseService
import com.shaadi.assignment.data.remote.NetworkService
import com.shaadi.assignment.data.repository.UserRepository
import com.shaadi.assignment.di.module.ApplicationModule
import com.shaadi.assignment.utils.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: ShaadiApplication)

    fun getNetworkService(): NetworkService

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getDatabaseService(): DatabaseService

    fun getUserRepository(): UserRepository

}