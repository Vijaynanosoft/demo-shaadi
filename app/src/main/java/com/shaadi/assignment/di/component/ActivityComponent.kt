package com.shaadi.assignment.di.component

import com.shaadi.assignment.di.ActivityScope
import com.shaadi.assignment.di.module.ActivityModule
import com.shaadi.assignment.ui.user.UserListActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: UserListActivity)

}