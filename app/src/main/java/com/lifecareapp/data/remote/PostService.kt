package com.lifecareapp.data.remote

import com.lifecareapp.data.remote.model.userlist.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface
PostService {

    companion object {
        //Endpoints
        private const val ENDPOINT_LIST_USER = "search/users"

        //Params
        private const val PARAM_QUERY_NAME = "q"

    }

    @GET(ENDPOINT_LIST_USER)
    fun searchUsers(@Query(PARAM_QUERY_NAME) userName:String): Single<SearchResponse>
}