package com.example.internshipt.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.internshipt.DataModels.FeedPost
import com.example.internshipt.Repository
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivityViewModel(private val repository: Repository): ViewModel() {

    lateinit var allFeedPosts: LiveData<List<FeedPost>>

    init {
        viewModelScope.launch {
            showProof()
        }
    }

    fun showProof() {
        allFeedPosts = repository.getAllPosts()
    }

    suspend fun insertFeedPost(feedPost: FeedPost) {
        repository.insert(feedPost)
    }

}

class MainActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}