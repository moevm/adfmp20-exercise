package com.example.myapplication.ui.TrainModule.Train

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.reflect.KProperty

class CacheManager(context: Context, key: String) {

    private val cache: SharedPreferences = context.getSharedPreferences(key, AppCompatActivity.MODE_PRIVATE)
    private val currentWeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    private val separator = "/"

    fun getTrainsResultByCurrentDay(): MutableList<Double> {
        return getTrainsResultByDay(currentWeekDay)
    }


    fun getTrainsResultByDay(day: Int): MutableList<Double> {
        val stringResult = cache.getString(day.toString(), null)?.split(separator)
        if (stringResult == null) { return mutableListOf() }
        if (stringResult.isNotEmpty()) {
            return stringResult.map { element -> element.toDouble() }.toMutableList()
        }
        return mutableListOf()
    }

    fun setTrainingResult(result: Double) {
        val cacheEditor = cache.edit()

        var dayResult = cache.getString(currentWeekDay.toString(), null)
        if (dayResult != null) { dayResult = dayResult + separator + result.toString() }
        else                   { dayResult = result.toString() }
        cacheEditor.putString(currentWeekDay.toString(), dayResult).apply()
    }
}