package com.example.internshipt.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.internshipt.DataModels.FeedPost

@Dao
interface FeedPostDao {

    @Insert
    suspend fun insert(feedPost: FeedPost)

    @Query("SELECT * FROM feed_post_table ORDER BY postId ASC")
    fun getAllPosts(): LiveData<List<FeedPost>>

}