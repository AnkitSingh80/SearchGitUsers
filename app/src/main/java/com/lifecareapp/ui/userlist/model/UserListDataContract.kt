package com.lifecareapp.ui.userlist.model

import com.lifecareapp.data.remote.model.Resource
import com.lifecareapp.data.remote.model.userlist.SearchResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class UserListDataContract{

    interface Repository{
        val searchUserResult: PublishSubject<Resource<Any>>
        fun searchUser(query:String)
    }
    interface Local{
        //in case we need to store data
        fun saveUser(userData:SearchResponse)
    }

    interface Remote{
        fun searchUser(query:String):Single<SearchResponse>
    }
}