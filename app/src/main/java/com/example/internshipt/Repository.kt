package com.example.internshipt

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.internshipt.Dao.FeedPostDao
import com.example.internshipt.DataModels.FeedPost
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository(private val dao: FeedPostDao) {

    @WorkerThread
    fun getAllPosts(): LiveData<List<FeedPost>> {
        return dao.getAllPosts()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(feedPost: FeedPost) {
        dao.insert(feedPost)
    }

}