package com.lifecareapp.ui.userlist.model

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.lifecareapp.data.remote.model.Resource
import com.lifecareapp.networking.Scheduler
import com.lifecareapp.utils.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.io.IOException

class UserListRepository(private val remote: UserListDataContract.Remote,
                         private val scheduler: Scheduler,
                         private val compositeDisposable: CompositeDisposable)
    : UserListDataContract.Repository {
    override val searchUserResult: PublishSubject<Resource<Any>> =
            PublishSubject.create<Resource<Any>>()



    override fun searchUser(query: String) {
        //performOnBackOutOnMain is an extension function
        searchUserResult.loading()
        remote.searchUser(query)
                .performOnBackOutOnMain(scheduler)
                .subscribe({
                    searchUserResult.success(it)
                }, {
                    error-> handleError(error)
                })
                .addTo(compositeDisposable)
    }

    private fun handleError(error: Throwable) {
        if(error is HttpException) {
            val code = error.response().code()
            val errorBody = error.response().errorBody()
            searchUserResult.ApiError(errorBody, code)
        }else{
            searchUserResult.failed(error)

        }
    }
}