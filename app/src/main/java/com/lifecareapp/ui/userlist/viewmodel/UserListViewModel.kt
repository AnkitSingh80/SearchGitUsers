package com.lifecareapp.ui.userlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.lifecareapp.data.remote.model.Resource
import com.lifecareapp.ui.userlist.di.PostDh
import com.lifecareapp.ui.userlist.model.UserListDataContract
import com.lifecareapp.utils.toLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject



class UserListViewModel(private val repo: UserListDataContract.Repository,
                        private val compositeDisposable: CompositeDisposable):ViewModel(){



    val userListLiveData: LiveData<Resource<Any>> by lazy {
        repo.searchUserResult.toLiveData(compositeDisposable)
    }

    fun apiGetUsers(name:String) {
        repo.searchUser(name)
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
        PostDh.destroyListComponent()
    }


}