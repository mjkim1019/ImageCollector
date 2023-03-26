package com.kakaobank.imagecollector.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.graphics.createBitmap
import com.google.gson.Gson
import com.kakaobank.imagecollector.BuildConfig
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.ERROR_PREFS
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_DEFAULT_RESULT
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_STORAGE
import com.squareup.moshi.Json
import java.util.LinkedList

object SharedPrefsManager {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    private val gson: Gson = Gson()

    fun init(context: Context) {
        prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        prefsEditor = prefs.edit()
    }

    private var favoriteList: LinkedList<Item> = LinkedList()

    fun addItemInFavoriteList(item: Item) {
        favoriteList.add(item)
        favoriteList.sortBy { it.savedDateTime }
        applyFavoriteListInPrefs()
    }

    private fun applyFavoriteListInPrefs() {
        val favoriteJson = gson.toJson(favoriteList)
        prefsEditor.putString(PREFS_STORAGE, favoriteJson)
        prefsEditor.commit()
    }

    fun getFavoriteListInPrefs(): LinkedList<Item> {
        val favoriteJson = prefs.getString(PREFS_STORAGE, PREFS_DEFAULT_RESULT)
        try {
            val list: LinkedList<Item> =
                gson.fromJson(favoriteJson, LinkedList::class.java) as LinkedList<Item>
            list.sortBy { it.savedDateTime }
            favoriteList = list
        } catch (e: ClassCastException) {
            Log.e(ERROR_PREFS, "Error in FavoriteList Type: ${e.message}")
        } catch (e: Exception) {
            Log.e(ERROR_PREFS, "getFavoriteListInPrefs: ${e.message}")
        }
        return favoriteList
    }

}