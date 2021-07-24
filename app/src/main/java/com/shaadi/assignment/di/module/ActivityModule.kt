package com.shaadi.assignment.di.module

import androidx.lifecycle.ViewModelProvider
import com.shaadi.assignment.data.repository.UserRepository
import com.shaadi.assignment.ui.base.BaseActivity
import com.shaadi.assignment.ui.user.UserListViewModel
import com.shaadi.assignment.utils.SchedulerProvider
import com.shaadi.assignment.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideUserViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository
    ): UserListViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(UserListViewModel::class) {
            UserListViewModel(schedulerProvider, compositeDisposable, userRepository)
        }).get(UserListViewModel::class.java)


}