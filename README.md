# SearchGitUsers
This Mvvm pattern uses github api and display the users in recyclere view.
it is built on top of existing components such as Fragments and architecture
components so it doesn't constrain you and is easy to incrementally adopt.

This repo is Kotlin first and Kotlin only. By being Kotlin only, we could leverage several powerful language features for a 
cleaner API. 

SearchGitUsers is built on top of the following existing technologies and concepts:

Kotlin<br>
Android Architecture Components<br>
RxJava<br>
Dagger 2<br>


# Kotlin Concepts

Sealed class for handling error from server.<br>
Data class(Resource class) a generic class that holds a value with its loading status.<br>
Extension function is used for rxjava where subscribing and obeserving boilerplate code is written.<br>

# Dagger 2
AppModule for providing Scheduler which will be injected at the time of network call.<br>
NetworkModule for providing retrofit object.<br>
UserListComponent which is reponsible for binding AppModule And Network Module and injecting to activity or fragment.<br>

# View Model
UserListViewModel which takes repo and disposable object and extends ViewModel().<br>
UserListViewModelFactory to create our custom viewModel extend it from ViewModelProvide.factory().<br>


