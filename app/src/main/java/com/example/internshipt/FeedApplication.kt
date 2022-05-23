package com.example.internshipt

import android.app.Application
import com.example.internshipt.Database.FeedPostDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope

class FeedApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { FeedPostDatabase.getDatabase(this, GlobalScope) }
    val repository by lazy { Repository(database.feedPostDao()) }
}