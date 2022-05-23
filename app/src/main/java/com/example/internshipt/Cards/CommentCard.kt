package com.example.internshipt.Cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.internshipt.DataModels.FeedComment
import com.example.internshipt.DataModels.FeedPost
import com.example.internshipt.R
import com.example.internshipt.ui.CommentActivity
import com.google.gson.Gson

@Composable
fun CommentCard(feedPost: FeedComment, context: Context, i: Int) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {
                    Card(
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        elevation = 2.dp
                    ) {
                        Image(
                            painterResource(feedPost.posterImage),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Column() {
                        Box(modifier = Modifier
                            .padding(7.dp, 7.dp, 0.dp, 0.dp)
                            .wrapContentSize()) {Text(feedPost.posterName,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )}
                        Box(modifier = Modifier.padding(7.dp, 0.dp, 0.dp, 0.dp)) {
                            Text("Public")
                        }
                    }
                }
                Icon(Icons.Filled.MoreVert, contentDescription = "")
            }
            Box(modifier = Modifier.padding(0.dp, 14.dp, 0.dp, 6.dp)) {
                Text(feedPost.commentContent, style = TextStyle(fontSize = 18.sp))
            }
            Box(modifier = Modifier.height(10.dp)) {

            }
            Box(modifier = Modifier.height(10.dp)) {

            }
            if (i==0) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    val checkedState = remember { mutableStateOf(false)}
                    Row() {
                        Checkbox(checked = checkedState.value, onCheckedChange = {
                            checkedState.value = it
                            if (it)
                            {
                                feedPost.noOfLikes++
                            } else {
                                feedPost.noOfLikes--
                            }
                        })
                        Box(modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)) {Text("" + feedPost.noOfLikes +" likes")}
                    }
                }
            }
        }
    }
}