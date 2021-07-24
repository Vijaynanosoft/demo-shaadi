package com.shaadi.assignment.ui.base

import androidx.lifecycle.ViewModel
import com.shaadi.assignment.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onCreate()

}