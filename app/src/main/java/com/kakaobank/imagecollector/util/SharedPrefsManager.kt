package com.kakaobank.imagecollector.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.kakaobank.imagecollector.BuildConfig
import com.kakaobank.imagecollector.model.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_PREFS
import com.kakaobank.imagecollector.util.ImageCollectorConst.ERROR_PREFS
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_DEFAULT_RESULT
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_STORAGE
import com.kakaobank.imagecollector.util.ImageCollectorConst.WARNING_PREFS
import java.time.LocalDateTime

object SharedPrefsManager {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, localDateTimeDeserializer)
        .registerTypeAdapter(LocalDateTime::class.java, localDateTimeSerializer)
        .create()

    private var favoriteList: LinkedHashMap<String, Item> = linkedMapOf()

    fun init(context: Context) {
        prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        prefsEditor = prefs.edit()
    }

    fun addItemInFavoriteList(item: Item) {
        if (item.savedDateTime != null) favoriteList[item.imgUrl] = item
        //favoriteList.sortByDescending { it.savedDateTime }
        applyFavoriteListInPrefs()
    }

    fun removeItemInFavoriteList(item: Item) {
        favoriteList.remove(item.imgUrl)
        applyFavoriteListInPrefs()
    }

    private fun applyFavoriteListInPrefs() {
        val favoriteJson = gson.toJson(favoriteList)
        prefsEditor.putString(PREFS_STORAGE, favoriteJson)
        prefsEditor.commit()
    }

    fun setFavoriteListFromPrefs() {
        val favoriteJson = prefs.getString(PREFS_STORAGE, PREFS_DEFAULT_RESULT)
        if (favoriteJson == PREFS_DEFAULT_RESULT) return
        try {
            val type = object : TypeToken<LinkedHashMap<String, Item>>() {}.type
            val list: LinkedHashMap<String, Item> =
                gson.fromJson(favoriteJson, type) as LinkedHashMap<String, Item>
            //list.sortByDescending { it.savedDateTime }
            favoriteList = list
            getFavoriteList()
        } catch (e: ClassCastException) {
            Log.e(ERROR_PREFS, "Error in FavoriteList Type: ${e.message}")
        } catch (e: java.lang.IllegalStateException) {
            Log.w(WARNING_PREFS, "It's not saved in the storage yet: ${e.message}")
        } catch (e: Exception) {
            Log.e(ERROR_PREFS, "setFavoriteListFromPrefs(): ${e.message}")
        }
    }

    fun getFavoriteList(): LinkedHashMap<String, Item> {
        Log.d(DEBUG_PREFS, "getFavoriteList: ${favoriteList.size}\n ${favoriteList}")
        return favoriteList
    }

}