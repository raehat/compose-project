package com.example.internshipt.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.internshipt.Cards.PostCard
import com.example.internshipt.DataModels.FeedPost
import com.example.internshipt.FeedApplication
import com.example.internshipt.TabItem
import com.example.internshipt.viewmodels.MainActivityViewModel
import com.example.internshipt.viewmodels.MainActivityViewModelFactory
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val mainActivityViewModel: MainActivityViewModel by viewModels{MainActivityViewModelFactory((
        application as FeedApplication).repository
     )}
    val context = this

    var feedPostsList = mutableStateListOf<FeedPost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
            mainActivityViewModel.allFeedPosts.observe(this, Observer {
                feedPostsList.addAll(it)
            })
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MainContent() {
        var charcha = TabItem("Charcha", { Charcha(feedPostsList, this) })
        var bazaar = TabItem("Bazaar", { Bazaar()})
        var profile = TabItem("Profile", { Profile()})
        val list = listOf(charcha, bazaar, profile)
        val pagerState = rememberPagerState(pageCount = list.size)
        Column(modifier = Modifier.fillMaxSize()) {
            Tabs(list, pagerState = pagerState)
            TabContent(tabs = list, pagerState = pagerState)
        }
    }

    @OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
    @Composable
    fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

        val scope = rememberCoroutineScope()

        TabRow(selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            contentColor = Color.Blue,
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions),
                    height = 3.dp,
                    color = Color.Blue)
            }) {

            tabs.forEachIndexed { index, tabItem ->

                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                    , text = { Text(text = tabItem.title) }, icon = { Unit },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray)

            }

        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TabContent(tabs:List<TabItem>, pagerState: PagerState) {
        HorizontalPager(state = pagerState) { page ->
            tabs[page].screens()
        }
    }

}

@Composable
fun Bazaar() {
    Column(horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Text(text = "bazaar", style = TextStyle(textAlign = TextAlign.Center))
    }
}

@Composable
fun Profile() {
    Column(horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Text(text = "profile", style = TextStyle(textAlign = TextAlign.Center))
    }
}

@Composable
fun Charcha(feedPostsList: SnapshotStateList<FeedPost>, context: Context) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        FeedPostList(feedPostsList = feedPostsList, context)
    }
}

@Composable
fun FeedPostList(feedPostsList: SnapshotStateList<FeedPost>, context: Context) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(feedPostsList) { item :FeedPost->
            PostCard(feedPost = item, context, 0)
        }
    }
}