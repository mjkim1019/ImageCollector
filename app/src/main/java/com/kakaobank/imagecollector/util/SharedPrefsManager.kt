package com.kakaobank.imagecollector.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.kakaobank.imagecollector.BuildConfig
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_PREFS
import com.kakaobank.imagecollector.util.ImageCollectorConst.ERROR_PREFS
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_DEFAULT_RESULT
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_STORAGE
import com.kakaobank.imagecollector.util.ImageCollectorConst.WARNING_PREFS
import java.time.LocalDateTime
import java.util.LinkedList

object SharedPrefsManager {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, localDateTimeDeserializer)
        .registerTypeAdapter(LocalDateTime::class.java, localDateTimeSerializer)
        .create()

    private var favoriteList: LinkedList<Item> = LinkedList()

    fun init(context: Context) {
        prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        prefsEditor = prefs.edit()
    }

    fun addItemInFavoriteList(item: Item) {
        if (item.savedDateTime != null) favoriteList.add(item)
        favoriteList.sortBy { it.savedDateTime }
        applyFavoriteListInPrefs()
    }

    fun removeItemInFavoriteList(item: Item) {
        favoriteList.remove(item)
        applyFavoriteListInPrefs()
    }

    private fun applyFavoriteListInPrefs() {
        val favoriteJson = gson.toJson(favoriteList)
        Log.d(DEBUG_PREFS, "applyFavoriteListInPrefs: ${favoriteJson}\n${favoriteList}")
        prefsEditor.putString(PREFS_STORAGE, favoriteJson)
        prefsEditor.commit()
    }

    fun setFavoriteListFromPrefs(): LinkedList<Item> {
        val favoriteJson = prefs.getString(PREFS_STORAGE, PREFS_DEFAULT_RESULT)
        Log.d(DEBUG_PREFS, "favoriteJson: ${favoriteJson}")
        try {
            val type = object : TypeToken<LinkedList<Item>>() {}.type
            val list: LinkedList<Item> =
                gson.fromJson(favoriteJson, type) as LinkedList<Item>
            list.sortBy { it.savedDateTime }
            favoriteList = list
            getFavoriteList()
        } catch (e: ClassCastException) {
            Log.e(ERROR_PREFS, "Error in FavoriteList Type: ${e.message}")
        } catch (e: java.lang.IllegalStateException) {
            Log.w(WARNING_PREFS, "It's not saved in the storage yet: ${e.message}")
        } catch (e: Exception) {
            Log.e(ERROR_PREFS, "setFavoriteListFromPrefs(): ${e.message}")
        }

        return favoriteList
    }

    fun getFavoriteList(): LinkedList<Item> {
        Log.d(DEBUG_PREFS, "getFavoriteList: ${favoriteList.size}\n ${favoriteList}")
        return favoriteList
    }

}