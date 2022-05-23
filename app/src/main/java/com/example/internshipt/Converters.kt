package com.example.internshipt

import androidx.room.TypeConverter
import androidx.room.util.StringUtil
import com.example.internshipt.DataModels.FeedComment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class Converters {

    @TypeConverter
    fun fromString(value: String): ArrayList<FeedComment> {
        val listType = object : TypeToken<ArrayList<FeedComment?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(lst: ArrayList<FeedComment>): String {
        val gson: Gson = Gson()
        val json: String = gson.toJson(lst)
        return json
    }

    @TypeConverter
    fun fromArrayListofInt(lst: ArrayList<Int>): String {
        if (lst.size==0)
            return ""
        var str: String = ""
        for (i in lst) {
            str += "" + i + ","
        }
        return str
    }

    @TypeConverter
    fun fromStrtoArrayList(str: String): ArrayList<Int> {
        if (str == "")
            return ArrayList()
        var arrInt: ArrayList<Int> = ArrayList()
        var strArray = str.split(',')
        for (i in strArray) {
            if (i!="") {
                arrInt.add(i.toInt())
            }
        }
        return arrInt
    }

}