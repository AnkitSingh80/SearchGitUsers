package com.lifecareapp.ui.userlist.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lifecareapp.ui.userlist.model.UserListDataContract
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(private val repository: UserListDataContract.Repository,
                                  private val compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(repository,compositeDisposable) as T
    }


}
