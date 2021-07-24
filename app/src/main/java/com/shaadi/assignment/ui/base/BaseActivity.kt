package com.shaadi.assignment.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.shaadi.assignment.ShaadiApplication
import com.shaadi.assignment.di.component.ActivityComponent
import com.shaadi.assignment.di.component.DaggerActivityComponent
import com.shaadi.assignment.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initObservers()
        initView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun initObservers() {

    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun initView(savedInstanceState: Bundle?)

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as ShaadiApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

}