package com.lifecareapp.utils

import android.app.Application
import com.lifecareapp.ui.userlist.di.AppModule
import com.lifecareapp.ui.userlist.di.DaggerUserListComponent
import com.lifecareapp.ui.userlist.di.UserListComponent
import com.lifecareapp.ui.userlist.di.UserListModule

class App:Application(){
    companion object {
        //lateinit var component: UserListComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        //component = DaggerUserListComponent.builder().appModule(AppModule(this)).build()
    }
}
