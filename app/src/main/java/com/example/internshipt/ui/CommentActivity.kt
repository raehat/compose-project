package com.example.internshipt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.internshipt.Cards.CommentCard
import com.example.internshipt.Cards.PostCard
import com.example.internshipt.DataModels.FeedComment
import com.example.internshipt.DataModels.FeedPost
import com.google.gson.Gson

class CommentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val feedPostStr = intent.extras?.getString("json")
            val feedPost: FeedPost = Gson().fromJson(feedPostStr, FeedPost::class.java)
            MainContent(feedPost)
        }
    }

    @Composable
    private fun MainContent(feedPost: FeedPost) {
        Column() {
            Box(Modifier.padding(16.dp, 25.dp, 0.dp, 25.dp)) {
                Row() {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                    Modifier.clickable {
                        onBackPressed()
                    })
                    Text(text = "Comments", Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp))
                }
            }
            PostCard(feedPost = feedPost, context = this@CommentActivity, i = 1)
            ListOfComments(feedPost.comments)
        }
    }

    @Composable
    fun ListOfComments(comments: ArrayList<FeedComment>) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(comments) { item ->
                CommentCard(feedPost = item, context = this@CommentActivity, i = 0)
            }
        }
    }
}