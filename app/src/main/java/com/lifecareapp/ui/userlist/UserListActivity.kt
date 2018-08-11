package com.lifecareapp.ui.userlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.lifecareapp.R
import com.lifecareapp.data.remote.model.Status
import com.lifecareapp.data.remote.model.userlist.SearchResponse
import com.lifecareapp.ui.userlist.di.PostDh
import com.lifecareapp.ui.userlist.viewmodel.UserListViewModel
import com.lifecareapp.ui.userlist.viewmodel.UserListViewModelFactory
import com.lifecareapp.utils.gone
import com.lifecareapp.utils.visible
import kotlinx.android.synthetic.main.activity_userlist.*
import javax.inject.Inject
import android.widget.EditText
import android.widget.ImageView
import com.lifecareapp.utils.isNetworkActiveWithMessage


class UserListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    private lateinit var userAdapter: UserAdapter

    private val component by lazy { PostDh.listComponent(applicationContext) }

    @Inject
    lateinit var viewModelFactory: UserListViewModelFactory

    private val viewModel: UserListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[(UserListViewModel::class.java)]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)
        component.inject(this)
        tvNoResult.visible()
        setUserAdapter()
        liveData()
        setSearchView()
        listeners()
    }


    private fun listeners() {
        svUsers.setOnQueryTextListener(this)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //api call to VIEW-MODEL which further call to repository
        if (isNetworkActiveWithMessage()) {
            if (query != "") {
                tvNoResult.gone()
                userAdapter.clearList()
                viewModel.apiGetUsers(query ?: "")
            }
        }
        //to dismiss the keyboard
        svUsers.clearFocus()
        return true
    }

    private fun liveData() {
        //observe the  loading , success and error state
        viewModel.userListLiveData.observe(this, Observer {
            it ?: return@Observer
            when (it.status) {
                Status.SUCCESS -> {
                    progress.gone()
                    val data = it.data as SearchResponse
                    if (data.totalCount == 0) {
                        tvNoResult.visible()
                    } else {
                        userAdapter.addUserList(it.data as SearchResponse)
                    }

                }
                Status.ERROR -> {
                    progress.gone()
                    // tvNoResult.visible()
                }
                Status.LOADING -> {
                    progress.visible()
                }
            }
        })
    }

    private fun setUserAdapter() {
        userAdapter = UserAdapter()
        rvListUser.layoutManager = LinearLayoutManager(this)
        rvListUser.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvListUser.adapter = userAdapter
    }

    private fun setSearchView() {
        val searchClose = svUsers.findViewById<ImageView>(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        searchClose.setImageResource(R.drawable.ic_cross_mini)
        val searchEditText = svUsers.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
    }
}
