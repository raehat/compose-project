package com.example.internshipt.DataModels

import androidx.room.Entity

@Entity(tableName = "feed_comment_table")
class FeedComment(
    val posterName: String,
    val posterImage: Int,
    val commentContent: String,
    var noOfLikes: Int
) {
}