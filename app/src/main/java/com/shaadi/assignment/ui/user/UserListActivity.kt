package com.shaadi.assignment.ui.user

import android.os.Bundle
import com.shaadi.assignment.R
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import com.shaadi.assignment.di.component.ActivityComponent
import com.shaadi.assignment.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : BaseActivity<UserListViewModel>(), UserListAdapter.IUpdateStatusHandler {

    lateinit var userAdapter: UserListAdapter

    override fun getLayoutId(): Int = R.layout.activity_user_list

    override fun initObservers() {
        super.initObservers()
        viewModel.allUser.observe(this, {
            userAdapter = UserListAdapter(it as ArrayList<User>, this)
            rvUserList.adapter = userAdapter
        })
    }


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun updateStatus(userStatus: UserStatus, id: Long) {
        viewModel.updateStatus(userStatus, id)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}