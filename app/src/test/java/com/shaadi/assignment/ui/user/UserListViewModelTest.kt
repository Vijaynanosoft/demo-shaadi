package com.shaadi.assignment.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.remote.response.Result
import com.shaadi.assignment.data.repository.UserRepository
import com.shaadi.assignment.utils.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testScheduler: TestScheduler

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var progressbarObserver: Observer<Boolean>

    @Mock
    private lateinit var errorStatusObserver: Observer<Boolean>

    @Mock
    private lateinit var userObserver: Observer<List<User>>
    private lateinit var userListViewModel: UserListViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        Mockito.doReturn(MutableLiveData<List<User>>())
            .`when`(userRepository)
            .getAll()

        userListViewModel = UserListViewModel(
            testSchedulerProvider,
            compositeDisposable,
            userRepository
        )
        userListViewModel.allUser.observeForever(userObserver)
        userListViewModel.errorStatus.observeForever(errorStatusObserver)
        userListViewModel.progressbar.observeForever(progressbarObserver)

    }

    @Test
    fun whenOnSuccess_ShouldSaveInDb() {

        Mockito.doReturn(Single.just(listOf<Result>()))
            .`when`(userRepository)
            .fetchUserList(Mockito.anyString())

        Mockito.doReturn(Single.just(listOf<Long>()))
            .`when`(userRepository)
            .saveUserList(Mockito.anyList())

        userListViewModel.onCreate()
        testScheduler.triggerActions()

        Mockito.verify(userRepository, times(1))
            .fetchUserList(Mockito.anyString())

        Mockito.verify(userRepository, times(1))
            .saveUserList(Mockito.anyList())

        assert(userListViewModel.progressbar.value == false)
        Mockito.verify(progressbarObserver).onChanged(true)
        Mockito.verify(progressbarObserver).onChanged(false)

    }


    @Test
    fun whenOnError_ShouldShowErrorState() {

        Mockito.`when`(userRepository.fetchUserList(Mockito.anyString()))
            .thenReturn(Single.error(Throwable("An error has occurred!")))

        userListViewModel.onCreate()
        testScheduler.triggerActions()

        Mockito.verify(userRepository, times(1))
            .fetchUserList(Mockito.anyString())

        Mockito.verify(userRepository, times(0))
            .saveUserList(Mockito.anyList())

        assert(userListViewModel.progressbar.value == false)
        assert(userListViewModel.allUser.value == null)
        Mockito.verify(progressbarObserver).onChanged(true)
        Mockito.verify(progressbarObserver).onChanged(false)
        Mockito.verify(errorStatusObserver).onChanged(false)
        Mockito.verify(errorStatusObserver).onChanged(true)
    }

    @After
    fun tearDown() {
        userListViewModel.progressbar.removeObserver(progressbarObserver)
        userListViewModel.errorStatus.removeObserver(errorStatusObserver)
        userListViewModel.allUser.removeObserver(userObserver)
    }


}