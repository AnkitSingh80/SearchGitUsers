package com.lifecareapp.ui.userlist.di

import android.content.Context
import com.lifecareapp.data.remote.PostService
import com.lifecareapp.networking.Scheduler
import com.lifecareapp.ui.userlist.UserListActivity
import com.lifecareapp.ui.userlist.model.UserListDataContract
import com.lifecareapp.ui.userlist.model.UserListRemoteData
import com.lifecareapp.ui.userlist.model.UserListRepository
import com.lifecareapp.ui.userlist.viewmodel.UserListViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class,UserListModule::class])
interface UserListComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    fun postService(): PostService
    fun scheduler(): Scheduler


    fun inject(userActivity: UserListActivity)



}

@Module
class UserListModule{

    @Provides
    fun UserListViewModel(repo: UserListDataContract.Repository, compositeDisposable: CompositeDisposable):
            UserListViewModelFactory = UserListViewModelFactory(repo,compositeDisposable)

    @Provides
    fun remoteData(postService: PostService): UserListDataContract.Remote = UserListRemoteData(postService)

    @Provides
    fun listRepo(remote: UserListDataContract.Remote, scheduler: Scheduler,
                 compositeDisposable: CompositeDisposable): UserListDataContract.Repository =
            UserListRepository(remote, scheduler, compositeDisposable)

    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun postService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

}
