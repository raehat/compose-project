package com.example.internshipt.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Comment

@Entity(tableName = "feed_post_table")
class FeedPost(
    @PrimaryKey val postId: Int,
    val posterName: String,
    val posterImage: Int,
    val postType: String,
    var noOfLikes: Int,
    val comments: ArrayList<FeedComment>,
    val feedContent: String,
    val listOfMedia: ArrayList<Int>
) {

}