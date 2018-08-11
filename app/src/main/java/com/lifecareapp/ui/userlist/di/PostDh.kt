package com.lifecareapp.ui.userlist.di

import android.content.Context
import javax.inject.Singleton

@Singleton
object PostDh {

    private var listComponent: UserListComponent? = null

    fun listComponent(applicationContext: Context): UserListComponent {
        if (listComponent == null)
            listComponent = DaggerUserListComponent.builder().appModule(AppModule(applicationContext)).build()
        return listComponent as UserListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }
}