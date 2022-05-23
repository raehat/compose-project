package com.example.internshipt.Database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.internshipt.Converters
import com.example.internshipt.Dao.FeedPostDao
import com.example.internshipt.DataModels.FeedComment
import com.example.internshipt.DataModels.FeedPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.internshipt.R

@TypeConverters(Converters::class)
@Database(entities = arrayOf(FeedPost::class), version = 1, exportSchema = false)
abstract class FeedPostDatabase : RoomDatabase() {

    abstract fun feedPostDao(): FeedPostDao

    private class FeedPostDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {

                    var dao = database.feedPostDao()

                    var lst: ArrayList<FeedComment> = ArrayList()
                    lst.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    lst.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    dao.insert(FeedPost(0, "Ayush Agarwal", R.drawable.poster_image, "Question", 3, lst, "What is Urvar?", ArrayList()))

                    var lst2: ArrayList<FeedComment> = ArrayList()
                    lst2.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    lst2.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    var lstImages: ArrayList<Int> = ArrayList()
                    lstImages.add(R.drawable.urwar_2)
                    dao.insert(FeedPost(1, "Ayush Agarwal", R.drawable.poster_image, "Marketing", 3, lst2, "Just landed on Urvar", lstImages))

                    var lst3: ArrayList<FeedComment> = ArrayList()
                    lst3.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    lst3.add(FeedComment("Ayush Agarwal", R.drawable.poster_image, "A yellow face with a frown and closed, downcast eyes, as if aching with sorrow or pain", 3))
                    dao.insert(FeedPost(2, "Ayush Agarwal", R.drawable.poster_image, "Soil Management", 3, lst3, "What is Urvar?", ArrayList()))


                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FeedPostDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FeedPostDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeedPostDatabase::class.java,
                    "feed_post_database"
                ).addCallback(FeedPostDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}