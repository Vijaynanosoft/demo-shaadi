package com.shaadi.assignment.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import com.shaadi.assignment.data.repository.UserRepository
import com.shaadi.assignment.ui.base.BaseViewModel
import com.shaadi.assignment.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class UserListViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val allUser = userRepository.getAll()
    val progressbar: MutableLiveData<Boolean> = MutableLiveData(true)
    val errorStatus: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onCreate() {
        getAllUsers()
    }

    private fun getAllUsers() {
        compositeDisposable.add(
            userRepository.fetchUserList("10")
                .flatMap { users ->
                    userRepository.saveUserList(users)
                }
                .subscribeOn(schedulerProvider.io())
                .doFinally {
                    progressbar.postValue(false)
                }
                .subscribe({
                }, {
                    if (allUser.value.isNullOrEmpty()) {
                        errorStatus.postValue(true)
                    }
                })
        )
    }


    fun updateStatus(userStatus: UserStatus, id: Long) {
        compositeDisposable.add(
            userRepository.updateStatus(userStatus, id)
                .subscribeOn(schedulerProvider.io())
                .subscribe()
        )

    }


}