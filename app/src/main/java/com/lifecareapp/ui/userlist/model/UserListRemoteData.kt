package com.lifecareapp.ui.userlist.model

import com.lifecareapp.data.remote.PostService
import com.lifecareapp.data.remote.model.userlist.SearchResponse
import io.reactivex.Single

class UserListRemoteData(private val postService: PostService) : UserListDataContract.Remote {
    override fun searchUser(query: String): Single<SearchResponse> =
            postService.searchUsers(query)
}